# Microservices dev/test env architecture
## Scope
This page will explain the current dev/test design for microservices which is being consumed from multiple frontend [other Dig environents] and also the deployment procedures that will be followed by microservices team going forward.

## ASIS design and drawbacks
Current microservices dev/test is the only env available for all other frontend server [Digital test dev envs]. 1 Zuul instance serving all the requests from different source. Due to single microservices stack in dev/test, any new changes that are built by the developers needs to be deployed and tested in the same environment which has impact on other teams - lack of availability and consitency in behavior.

Change in backend also impacts both microservices and otehr testing teams who use microservices dev/test environment.

## New design and advantage
* Introduced additonal Zuul setup as stagging env. So, from now on there will be 2 instances of Zuul running in microservice dev/test environment. **1 -> pre-prod [url as http://<IP>/<ms_endpoint>] and 2 -> stage [url as http://<IP>/stage/<ms_endpoint>].**
* Stage will be used by the microservices development and testing team for deploying and testing new features that are built per requirment.
* All other development teams will be consuming the pre-prod end points and so any changes from microservices dev teams will not impact them at all.
* Process flow : Microservices team develop and deploy the changes in stage => Testing team will test the features in stage and sign-off => **The code will be promoted to Pre-prod [any issue with the new code will be identified well before moving it to prod - breaking changes]** => once all good in pre-prod, the code will be promoted to production.
* Any team who are currently blocked with the unavaialbility of the microservices dev/test is now **UNBLOCKED**

## Script and yml file locations
* Server : ph1083302
* IP : 10.224.70.49
* External IP : 193.67.160.9
* Nomad startup script : /local/home/custadm/stage-nomad-jobs
* YML STASH repo URL : https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/stage
