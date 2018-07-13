See Nomad commands reference [here](https://www.nomadproject.io/docs/commands/index.html).

# CLI

## Step 1: Checking jar availability
Ensure the latest version of the microservice code has built successfully and is uploaded to s3 and to .49 (via fetch microservice). The jars are located in .49 under [Local ms jar repo](http://10.224.70.49/micro-service-jars/)

If the jars are available then progress with Step 2 below to deploy the microservice. Else run below command to download the jars from S3 into .49

Execute the following command 
#### From .49 box
`curl -X GET 'localhost:4010/fetch-jar?microService=<MS_NAME>&version=<VERSION>' -H 'auth_token: <auth token>'`

#### From any other environment
`curl -X GET 'https://<Digital1_extenal/internal_ip>_OR_<PREPROD_External/InternalIP>/v1/fetch-micro-service-jar-from-s3/fetch-jar?microService=<MS_NAME>&version=<VERSION>' -H 'auth_token: <Check in preprod server>' -H 'cache-control: no-cache' -H 'content-type: application/vnd.api+json'  -H 'x-client-id: cbdcfa43-dd67-4c38-b418-83572a936fca' -k`

_For auth_token get in touch with @Aravind or DEVOPS_

Please refer to [Fetch jar MS](https://github.com/ConnectedHomes/bg-rest-api/tree/master/fetch-micro-service-jar) for more details

## Step 2: Deploy a new version of a microservice 

### STAGE - 10.224.70.11 - External ip: 193.67.160.70
1. Login to stage server through putty as custadm user and switch to root user.
2. Change directory to `/local/home/custadm/nomad-stage`.
1. Open the hcl file for the microservice and update the jar version number in 2 places - the jar name under args section and artifact section. 

### PREPROD- 10.224.70.120 - External ip: 193.67.160.161
1. Login to stage server through putty as custadm user and switch to root user.
2. Change directory to `/local/home/custadm/nomad`.
1. Open the hcl file for the microservice and update the jar version number in 2 places - the jar name under args section and artifact section.
3. The preprod is setup with fabio that runs on port 9999 so there are no haproxy in new preprod.

### PROD - 10.224.61.156
1. Login to stage server through putty as custadm user and switch to root user.
2. Change directory to `/local/home/custadm/nomad`.
1. Open the hcl file for the microservice and update the jar version number in 2 places - the jar name under args section and artifact section. 

## Step 3: Deploy and check application availability

### Get a nomad plan command  
> `nomad plan <xxxx.hcl>`

Copy and execute the command returned from nomad **plan**.  The **plan** command invokes nomad in dry run mode when a new job is submitted or or an existing job is updated.  This also gives a check-index as part of the resulted command.   When running the job with the check-index flag, the job will only be run if the server side version matches the job modify index returned. If the index has changed, another user has modified the job and the plan's results are potentially invalid.

### Check microservice status and get an allocation ID
>  `nomad status <job name given in the hcl>`
>  `nomad alloc-status <allocation_id from above command>`

Login to corresponding box/server after identifying the instance

### Check logs
> `nomad logs <allocation id>` _to output the entire log_ 

> `nomad logs <allocation id> | more` _to browse logs page by page_

> `nomad logs <allocation id> | grep "<Date time - logging format>" | more` _to browse logs from a specific date time_

OR

Goto the job logs directory `cd /var/lib/nomad/alloc/<allocation_id>/alloc/logs`

IF there are no changes to the hcl file and microservice needs a restart then follow Step 4 below....

## Step 4: Stop and Start microservices

### Stop microservice
> `nomad stop <job name given in the hcl>`

### Start microservice 
> `nomad run <xxxxx.hcl>`

Finally login to consul on that specific environment and check if the service has registered successfully and it is healthy [GREEN]