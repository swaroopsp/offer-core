**Table of contents**

1. [Pre-production and Staging Environments](#preprod-stage)
    1. [Monitoring tools (hash-ui)](#monitoring)
1. [Live data](#livedata) 
1. [Testing](#testing) 
    1. FITNESS stage
    1. FITNESS pre-prod
    1. [Checklist for epic to master PR merge](#checklist-for-epic-to-master-pr-merge)
1. [Documentation](#documentation) 
1. [Issues](#issues) 
1. [RoadMap / Board](#roadmap)
1. [Vision](#vision)
1. [Knowledge base](#knowledgebase)
    1. Common errors and how to fix them 
1. [Code](#code)
1. [Team](#team)
1. [Developer / Tools](#tools)
1. [SAP Email Distribution Lists](#sap-dls)
1. [Old stack](#old-stack)

<a name="preprod-stage"></a> 
# Pre-production and Staging Environments
Browse the list of currently deployed microservice jars and search the logs for each at:
  * [stage](http://193.67.160.9/control_tower/index.html?env=stage)
  * [preproduction](http://193.67.160.9/control_tower/index.html?env=preprod)

❗️ Note, staging and preproduction environments use the https protocol.

❗️ ~Note, to reach bg-core-uaa directly on stage, the port :8443 should be appended to the URL host (e.g. `https://<stage_IP>:8443/uaa/oauth/token`)~

## Preprod internal IP address (centrica LAN only)
> 10.224.70.120

## Preprod external IP address
> 193.67.160.161

❗️ Note, Digital 1's web layer is configured to route requests to preprod (rather than stage or elsewhere).  Microservices in preprod can be accessed via the Digital 1 external ip `https://193.67.163.82/<microservice_endpoint>`

<a name="monitoring"></a>
### Monitoring
* stage - 10.224.70.11:9001/ external https://193.67.160.70
* pre-prod (digital 1): 10.224.70.120:9001/ external https://193.67.160.161
* pre-prod2 (digital 8): 10.224.70.119:9001/

## Stage internal IP address (centrica LAN only)
> 10.224.70.11
## Stage external IP address
> 193.67.160.70

<a name="livedata"></a> 
# Live Data

## Production

[App Dynamics](https://centrica.saas.appdynamics.com/controller/#/location=AD_HOME_OVERVIEW)

You need an account to access App Dynamics: request one from Jas Grewal (@jasgrewal).  Use `centrica` as the account and your app credentials to login. 

<a name="testing"></a> 
# Testing

Different automated test-suite recurring runs.

[more FITNESS info](https://github.com/ConnectedHomes/bg-rest-api/wiki/FITNESS-test-infrastructure)

## FITNESS stage

[Latest FITNESS report](http://193.67.160.9/fitness_testruns/stage/latest/report.html)

[Trigger a run](http://sample-env.mmdkerhmnh.us-west-2.elasticbeanstalk.com/test/interface/fitness-stage)

## FITNESS Preprod

[Latest FITNESS report](http://193.67.160.9/fitness_testruns/latest/report.html)

[Trigger a run](http://sample-env.mmdkerhmnh.us-west-2.elasticbeanstalk.com/test/interface/fitness-preprod)

## Checklist for epic to master PR merge
![](https://github.com/ConnectedHomes/bg-rest-api/blob/master/documents/epic-to-master-PR-checklist.png)
Number of checks doesn't matter. It should say 'All checks have passed'.

Click on `Merge pull request` and delete the branch.


<a name="documentation"></a> 
# Documentation

Aggregated API documentation

[Production End Points in Apps](https://docs.google.com/spreadsheets/d/10N7SIUOsCfQ5Lti7smu88nCMGaTQPxOSVdCWXwW9cfc/edit?usp=sharing)

<a name="issues"></a> 
# Issues

<a name="roadmap"></a> 
# RoadMap / Board

[project board](https://github.com/ConnectedHomes/bg-rest-api/projects/4)

<a name="vision"></a> 
# Vision


<a name="knowledgebase"></a> 
# Knowledge base

[Error knowledge base](https://github.com/ConnectedHomes/bg-ms-knowledge-base/issues)

<a name="code"></a> 
# Code

* https://github.com/ConnectedHomes/bg-core-addresses
* https://github.com/ConnectedHomes/bg-core-appointments
* https://github.com/ConnectedHomes/bg-core-audits
* https://github.com/ConnectedHomes/bg-core-contact
* https://github.com/ConnectedHomes/bg-core-content
* https://github.com/ConnectedHomes/bg-core-customers
* https://github.com/ConnectedHomes/bg-core-emails
* https://github.com/ConnectedHomes/bg-core-energy
* https://github.com/ConnectedHomes/bg-core-energysales
* https://github.com/ConnectedHomes/bg-core-home-move
* https://github.com/ConnectedHomes/bg-core-home-services
* https://github.com/ConnectedHomes/bg-core-insurance
* https://github.com/ConnectedHomes/bg-core-meters
* https://github.com/ConnectedHomes/bg-core-payments
* https://github.com/ConnectedHomes/bg-core-questionnaires
* https://github.com/ConnectedHomes/bg-core-uaa
* https://github.com/ConnectedHomes/bg-core-users

* https://github.com/ConnectedHomes/bg-modules
* https://github.com/ConnectedHomes/bg-mod-vault-ssl-config
* https://github.com/ConnectedHomes/bg-mod-ws-framework

* https://github.com/ConnectedHomes/bg-rest-api

* https://github.com/ConnectedHomes/bg-support-config
* https://github.com/ConnectedHomes/bg-support-zuul

<a name="team"></a> 
# Team

* @czerwina (manager/architect)
* @deeleahy (project management / scrum)
* @abhinav (tester)
* @gokulchandar (tester)
* @latha (tester)
* @nila (dev, devops)
* @radhaka2 (dev, devops)
* @ramesh (dev, devops)
* @shilpa (CI)
* @ryanp (dev)
* @giritharan-rangaraj (dev)
* @paulhopkins11 (dev)
* @mikaelwozniak (dev)
* @haneesa (dev)
* @joe (dev)
* @vikrant (dev)

<a name="sap-dls"></a> 
# SAP Email Distribution Lists
For any SAP related issues use following DLs
 ##### For PI calls - SAPPITeam@centrica.com
 ##### For netweaver/odata calls - SAPGatewayTeam@centrica.com
 ##### For SAP PI production realted queries - SOA Integration Support Team (IntegrationSupportTeam@centrica.com) 

<a name="old-stack"></a> 
# Old Stack

### SVN
* URL: https://193.67.162.87/svn/
* Credentials: your LAN id and password

### Env - Digital 01
* Log location : /domains/wl_fit/crx-quickstart/logs

<a name="tools"></a> 
# Development tools


## Utility programs 

* https://github.com/ConnectedHomes/bg-ms-helpscripts - convert entities between json/apib/java

## URLs for local dev environment systems

* http://localhost:8500/ - consul
* http://localhost:3000/ - nomad
* http://localhost:15672/ - rabbitmq

## Running microservices locally

* How to run locally when investigating issues that require many services and dependencies. Some options: 
   * use mocks for back ends
   * use mocks for a microservice we depend on
   * run the dependency(-ies) but inject a static object directly in the resource GET (while keeping the other endpoints normal)
   * hack Feign clients and interceptors to use microservices deployed on stage/preprod as opposed to local
   * “just develop locally but only test on stage”…….
   * mix of those
 
https://github.com/RyanPridgeon/boot-wiremock
