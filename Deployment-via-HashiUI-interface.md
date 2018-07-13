# How to use HashiUI to Deploy microservices in environments

Please follow document [Hashi UI deployment Doc](https://github.com/ConnectedHomes/bg-rest-api/blob/master/documents/HashiUI_deployment_steps.docx)
to deploy/promote new version of microservice into Stage environment.

Please note: The HashiUI on other environments are <b>READONLY</b> and so please don't try the same anywhere else....**`If multiple developers are working on the same microservice then please talk to each other before making the changes to avoid any confusion.`**

# Also please READ below statements before using the tool

## Dont's:

`Do not change any values apart from the jar version. If you do then the job will fail.`<br/>
`Do not increase the instances/tasks which will result in memory breach and the complete system will go down.`<br/>
`If you need new instances or microservices to be deployed, please contact Dev ops. Ex: for creating epic versions.`<br/>

## Do's
**`Please make sure you take a backup of the current version of the jar before making the change so that you can revert the version if there are any issues in deployment`**<br/>