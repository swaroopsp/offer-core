# Monalisa2 - Instrumenting a microservice

Any microservice can be instrumented this way:

## Basics

### $microservice.yml 

```
m2logging: true
```

### build.gradle

```
//spring cloud sleuth so that calls across microservices sha
compile('org.springframework.cloud:spring-cloud-starter-sleuth')

//logback logging to tcp 
compile('net.logstash.logback:logstash-logback-encoder:4.9')
compile('ch.qos.logback:logback-classic:1.2.3')
compile('ch.qos.logback:logback-core:1.2.3')
compile('org.codehaus.janino:janino:3.0.6')	   

//2018 version of our shared mod code
compile('uk.co.britishgas.api:mod-utils:0.19.0')
compile('uk.co.britishgas.api:mod-feign:1.11.5')
compile('uk.co.britishgas.api:mod-ws-framework:1.12.1')
compile('uk.co.britishgas.api:mod-rs-framework:4.28.0')
```

### Application.java
```
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
```

### logback.xml (src/main/resources)

This file says that log output is both sent to stdout and to logstash via tcp. 

```
<configuration>
	<include resource="org/springframework/boot/logging/logback/defaults.xml" />

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
        </encoder>
        <filter class="ch.qos.logback.core.filter.EvaluatorFilter">
            <evaluator>
                <expression>return
                    logger.equals("uk.co.britishgas.api.utils.log.M2Log");</expression>
            </evaluator>
            <OnMismatch>NEUTRAL</OnMismatch>
            <OnMatch>DENY</OnMatch>
        </filter>
    </appender>

	<appender name="M2_STASH"
		class="net.logstash.logback.appender.LogstashTcpSocketAppender">
		<destination>127.0.0.1:4560</destination>
		<filter class="ch.qos.logback.core.filter.EvaluatorFilter">
			<evaluator>
				<expression>return
					logger.equals("uk.co.britishgas.api.utils.log.M2Log");</expression>
			</evaluator>
			<OnMismatch>DENY</OnMismatch>
			<OnMatch>NEUTRAL</OnMatch>
		</filter>
		<encoder class="net.logstash.logback.encoder.LogstashEncoder">
			<provider
				class="net.logstash.logback.composite.loggingevent.ArgumentsJsonProvider" />
		</encoder>
	</appender>

	<root level="debug">
		<appender-ref ref="CONSOLE" />
		<appender-ref ref="M2_STASH" />
	</root>
</configuration>
```
### feign code changes (external feign like NWG only)

to track the external feign calls to NWG, you need to use our Client wrapper class.

```
  @Value("${m2logging:false}")
  private Boolean m2logging;
```

```
//instead of this
.client(new Client.Default(SslUtils.sslSocketFactory(), new TrustAllHostnameVerifier()))
//wrap in a M2LoggingClient like this: 
.client(new M2LoggingClient( new Client.Default(SslUtils.sslSocketFactory(), new TrustAllHostnameVerifier()) , m2logging  ))
```

*Warning*; this class uses a newer method from Feign; Feign.Response.builder. You might get a NoSuchMethod error if the feign version being used isn't new enough. A good fix for this is normally to add a new version of OpenFeign at the end of your build.gradle dependencies; `compile('io.github.openfeign:feign-core:9.6.0')`


Update: if you use the latest `ODataClientFactory.feignClient` method, you get that for free. 

And again, this is for odata Feign, NOT inter-microservice feign communication where there is no need to do anything (traffic caught by destination service via Filter)

### ms-interface.yml

if your microservice uses an external backend not covered till now, you might have to update the following property in ms-interface yml (the microservice serving the monalisa2 report)

```
participantpatterns: SAP_NWG;/sap/;SAP_PI;XISOAPAdapter;CDL;/strataws/;QAS;7002
```
It is a list of pairs "name";"pattern". Add one entry that convert a unique part of the external backend url into a nice friendly name that will appear in the report. 

For example any url that contains `XISOAPAdapter` will appear as `SAP_PI` in monalisa2.


## Custom

There are some very specific cases where extra steps must be taken for certain kind of traffic to be monitored normally. 

### custom web service calls

The addresses microservice calls QAS to get address data but doesn't use our `ws-framework` standard lib to do it. It has its own variation of the same set of classes. For this to be covered, the following must be created:

```
    @Override
    public WebServiceTemplate template() {
        WebServiceTemplate template = new WebServiceTemplate(messageFactory());
        ...
        if (isM2LoggingEnabled())
           template.setInterceptors(new ClientInterceptor[] {new WsM2LoggingClientInterceptor(soapEndpoint())});
        ...
    }

/*
this assume we are in the WebServiceConfiguration object which has the following method attached to our yml property

  @Value("${m2logging}")
  private Boolean m2logging;

public boolean isM2LoggingEnabled() {
    return m2logging;
}

*/

```
***
If you use custom executor, make sure they are tracable, more information here: https://cloud.spring.io/spring-cloud-sleuth/single/spring-cloud-sleuth.html#_executor_executorservice_and_scheduledexecutorservice

If you use our `mod-utils` fork&join or the default @async executor, you should have nothing to do. 




## Troubleshooting

If nothing happen, make sure that the following logger is directly or indirectly at logging level INFO or below. 
```
uk.co.britishgas.api.utils.log: INFO
```


***


If you get weird sap NWG failures and the following exception in the logs:
```
Caused by: java.lang.NoSuchMethodError: feign.Response.builder()Lfeign/Response$Builder;
    at uk.co.britishgas.api.core.feign.logging.M2LoggingClient.copyResponse(M2LoggingClient.java:119)
```
Make sure your microservice is not using an old version of the official `feign-core` jar. this might happen if your gradle specify an old version number for ribbon and such. 
```
      //feign needs to be newer than the old one associated with ribbon above. 
	    compile group: 'io.github.openfeign', name: 'feign-core', version: '9.5.0'
```

***



## Technical notes

internally, monalisa2 uses spring cloud sleuth, logstash, elasticsearch among other things. 

# Run it locally

For most people, they use it in preprod. But it is possible to run it locally of course: 

Install elasticsearch and logstash 2.4 and run them like this:

`./elasticsearch-2.4.0/bin/elasticsearch`

`./logstash-2.4.0/bin/logstash -f ./elk-example/logstash/conf/monalisa2a.conf `

Run the `ms-interface` microservice locally, making sure it is connected to elasticsearch with that sort of config:

```
elasticsearch:
  clustername: mkyong-cluster
  host: 127.0.0.1
  port: 9300
```

list participants your ecosystem is dealing with:

`participantpatterns: SAP_NWG;/sap/;SAP_PI;XISOAPAdapter;CDL;/strataws/;QAS;7002`

Go on the local monalisa page with your browser and start using normally: 

`/bg-ms-interface/monalisa2/index.html?env=local`








