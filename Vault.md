# Introduction

Vault is used in the test & live environments for SSL/security management. Microservices should run locally on our machines with Vault disabled, and without Vault installed and configured on your machine. However, the Microservices should be configured to use Vault when in the test and live environments.

# Integrating Vault into a microservice

To use Vault, the microservices requires few changes.

## build.gradle

The **build.gradle** requires the dependency;

	`compile('uk.co.britishgas.vault:spring-cloud-vault-config-ssl:0.1.0')`

And the repositories;

```
    repositories {
        ...
        maven {url "https://repo.spring.io/milestone"}
        maven {url "https://repo.spring.io/snapshot"}
        maven {url "https://artifacts.alfresco.com/nexus/content/repositories/public"}
```

This allows the spring-cloud-vault-config-ssl module to be added to the classpath, which will perform Vault related functionality when the service starts.

## bootstrap.yml

Some config is also required in **src/main/resources/bootstrap.yml**;

```
spring:
  cloud:
    config:
      uri: ${config.path:http://localhost:8888}
    vault:
      enabled: ${vault.enabled:false}
      host: localhost
      port: 8200
      authentication: TOKEN
      token: ${vault.token:}
      scheme: https
      pki:
        reuse-valid-certificate: false
        enabled: ${vault.enabled:false}
        role: ${pki.role-name:microservice}
        backend: ca
        common-name: localhost
        alt-names: ${pki.alt-names:}
        ip-sans: ${pki.ip-sans:}
```

This allows Vault to be enabled and configured by Nomad scripts when in deployment. The default values disable Vault and use a non-HTTPS config URL so that you can use the Microservice locally without Vault and SSL.

When being deployed, values are passed as command line arguments to enable and configure vault.