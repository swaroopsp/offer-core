# config

     prints formatted json for the given micro service

     e.g. 

     config meters/v1

     prints
```Json
{
   "label": "master",
   "name": "meters",
   "profiles": [
       "v1"
   ],
   "propertySources": [
       {
           "name": "http://10.114.69.117/stash/scm/oa/support-config-test.git/v1/meters-v1.yml",
           "source": {
               "health.config.enabled": false,
               "hystrix.command.default.execution.isolation.strategy": "THREAD",
               "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds": 35000,
               ....
               ....
       }
   ]
}
```

    config meters

    prints the default configuration


    config meters/v1 log

    will do grep on json output and print properties matching log
```
        "logging.level.org.springframework.ws.client.MessageTracing.received": "TRACE",
        "logging.level.org.springframework.ws.client.MessageTracing.sent": "TRACE",
```
