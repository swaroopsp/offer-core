## Goal
The goal of the POC is to prove that multiple microservice calls can be orchestrated into a single request using Spring Cloud Dataflow

## Spring Cloud
> Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy, control bus, one-time tokens, global locks, leadership election, distributed sessions, cluster state). Coordination of distributed systems leads to boiler plate patterns, and using Spring Cloud developers can quickly stand up services and applications that implement those patterns. They will work well in any distributed environment, including the developer's own laptop, bare metal data centres, and managed platforms such as Cloud Foundry.

## Spring Cloud Dataflow

> Spring Cloud Data Flow is a toolkit for building data integration and real-time data processing pipelines.
> Pipelines consist of Spring Boot apps, built using the Spring Cloud Stream or Spring Cloud Task microservice frameworks. This makes Spring Cloud Data Flow suitable for a range of data processing use cases, from import/export to event streaming and predictive analytics.

## Installation

There are several components required for Dataflow to run - Dataflow Server, Zookeeper, Kafka, Dataflow Shell.

Kafka uses Zookeeper. Whether we could use Consul instead is unknown.
Kafka is preferred over RabbitMQ as the configuration is simpler (Dataflow server creates the topics automatically). NOTE: Maybe we should look to replace RabbitMQ with Kafka in our MS stack anyway?

I followed these instructions for downloading the required libraries http://docs.spring.io/spring-cloud-dataflow/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started

## Starting the environment

I wrote this script to start everything up quickly
```
#!/bin/bash

SPRING_CLOUD_DIR=~/dev/spring-cloud-dataflow
KAFKA_DIR=$SPRING_CLOUD_DIR/kafka_2.11-0.11.0.0

xterm -T "1 Spring Cloud Dataflow" -geometry 210x50 -e "java -jar $SPRING_CLOUD_DIR/spring-cloud-dataflow-server-local-1.2.3.RELEASE.jar" &
xterm -T "2 Zookeeper" -geometry 210x50 -e "$KAFKA_DIR/bin/zookeeper-server-start.sh $KAFKA_DIR/config/zookeeper.properties" &
sleep 3
xterm -T "3 Kafka" -geometry 210x50 -e "$KAFKA_DIR/bin/kafka-server-start.sh $KAFKA_DIR/config/server.properties" &
sleep 10
xterm -T "4 Spring Cloud Shell" -geometry 210x50 -e "java -jar $SPRING_CLOUD_DIR/spring-cloud-dataflow-shell-1.2.3.RELEASE.jar" &
echo "Now access the UI at http://localhost:9393/dashboard/index.html"
```
Note that Dataflow Server uses Spring Config so if you have our config microservice running it will try to use it and get an invalid config so stop this first.

Dataflow Server by default will use an in-memory database so you will need to install applications and streams each time you start.
## Building custom applications
It was necessary to write a custom `httpclient` Processor application to support our data flow. You should clone this repo: https://github.com/ConnectedHomes/bg-dataflow-groovy-httpclient and build the jar using `./gradlew clean build`
## Custom Stream Scripts
I wrote custom scripts in Groovy to support data transformation. You should clone this repository: https://github.com/ConnectedHomes/bg-dataflow-transform
## Installing the applications and Stream
An application is a component that is used in a stream. I used 3 - `http`,`groovy-httpclient` and `log`. `http` is a Source application, `groovy-httpclient` is a Processor application and `log` is a Sink application.

The stream, once installed should be like this:
```
input: http -> customer: groovy-httpclient -> premises: groovy-httpclient -> bank: groovy-httpclient -> services-contracts: groovy-httpclient -> output: log 
```
So at the end of the stream the html response from the call to services-contract should be logged.
I wrote this script called `create` to install the stream and application:
```
# Sample apps for http and logging
app register --name http --type source --uri maven://org.springframework.cloud.stream.app:http-source-kafka-10:1.2.0.RELEASE
app register --name log --type sink --uri maven://org.springframework.cloud.stream.app:log-sink-kafka-10:1.2.0.RELEASE
app register --name groovy-httpclient --type processor --uri file://home/phopkins/dev/spring-cloud-dataflow/dev/groovyhttpclient/build/libs/groovyhttpclient-0.0.1-SNAPSHOT.jar

stream create hivestream --definition 'input: http --server.port=8888 --spring.cloud.stream.bindings.output.contentType=application/json | customers: groovy-httpclient --groovy-httpclient.url-expression="https://193.67.160.70/v1/customers".toString() --groovy-httpclient.http-method=POST --groovy-httpclient.input-script=file:/home/phopkins/dev/mock-hive/src/main/resources/customers-input.groovy --groovy-httpclient.output-script=file:/home/phopkins/dev/mock-hive/src/main/resources/customers-output.groovy | premises: groovy-httpclient --groovy-httpclient.url-expression="https://193.67.160.70/v1/premises".toString() --groovy-httpclient.http-method=POST --groovy-httpclient.input-script=file:/home/phopkins/dev/mock-hive/src/main/resources/premises-input.groovy --groovy-httpclient.output-script=file:/home/phopkins/dev/mock-hive/src/main/resources/premises-output.groovy | bank-accounts: groovy-httpclient --groovy-httpclient.url-expression="https://193.67.160.70/v1/bank-accounts".toString() --groovy-httpclient.http-method=POST --groovy-httpclient.input-script=file:/home/phopkins/dev/mock-hive/src/main/resources/bank-accounts-input.groovy --groovy-httpclient.output-script=file:/home/phopkins/dev/mock-hive/src/main/resources/bank-accounts-output.groovy | services-contracts: groovy-httpclient --groovy-httpclient.url-expression="https://193.67.160.70/v1/services-contracts".toString() --groovy-httpclient.http-method=POST --groovy-httpclient.input-script=file:/home/phopkins/dev/mock-hive/src/main/resources/services-contracts-input.groovy --groovy-httpclient.output-script=file:/home/phopkins/dev/mock-hive/src/main/resources/services-contracts-output.groovy | output: log'

stream deploy hivestream --properties "deployer.input.memory=50m,deployer.customers.memory=50m,deployer.premises.memory=50m,deployer.bank-accounts.memory=50m,deployer.services-contracts.memory=50m,deployer.output.memory=50m"
```
* NOTE: The separate `stream deploy` call is required if we want to set any JVM variables. I set the memory so it didn't consume my whole laptop RAM.
* NOTE: The paths to the groovy scripts. You should amend this to reflect the location of your cloned https://github.com/ConnectedHomes/bg-dataflow-transform repository
* NOTE: The path to the built `groovy-httpclient` jar

I wrote this script called `destroy` to install the stream and application:
```
stream destroy hivestream
app unregister --name http  --type source
app unregister --name log  --type sink
app unregister --name groovy-httpclient  --type processor
```
### Using the Dataflow Shell
You use the Dataflow Shell to deploy streams. You can use the web app but that doesn't scale.
To work out what is deployed 
```
> runtime apps
```
To deploy everything
```
> script --file /home/phopkins/dev/spring-cloud-dataflow/create
```
To undeploy everything
```
> script --file /home/phopkins/dev/spring-cloud-dataflow/destroy
```