Guide for using Consul discovery locally

## Installing and running Consul

https://www.consul.io/downloads.html

Download the Consul binary from above and extract the binary `consul`. Install/add to your system path (on Ubuntu; `sudo cp consul /usr/local/bin/`)

Run Consul in development mode using

`consul agent -dev`

This will create a simple single instance non persisting Consul server at http://localhost:8500

Visit http://localhost:8500 in your browser for a UI to check the status of each service.

## Using with the microservices

The Spring Consul discovery client can be added using the Maven dependency `org.springframework.cloud:spring-cloud-starter-consul-discovery`

Without config, the Spring consul discovery client will attempt to connect to http://localhost:8500, which should usually be fine.

Consul defaults to using a health endpoint for each microservice at `/health`. However we have a lot of our microservices configured to have the health endpoint in an unusual place. Consul needs a successful health endpoint check to expose the microservice to other services.

For example, some of our microservices have

`server.servlet-path = /management`

Which means the health endpoint is at /management/health.

If the health endpoint isn't at ```/health```, use the ```discovery.healthCheckPath``` property

```
spring.cloud.consul.discovery.healthCheckPath = ${server.servlet-path}/health
```

Example config

```
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        healthCheckPath: ${server.servlet-path}/health
        healthCheckInterval: 5s
        ```
