Elastic Container Registry (ECR) is the aws hosted docker registry we use for storing our build docker images. This page lists some common operations you might wish to perform.

### Prerequisites
* awscli installed and configured

## Docker Login
Before you attempt to download any docker images from ECR you must perform a docker login
```
$(aws ecr get-login --no-include-email --region eu-west-2)
```
Example output
```
> $(aws ecr get-login --no-include-email --region eu-west-2)
Login Succeeded
```
## List repositories
```
aws ecr describe-repositories
```
Example output
```
> aws ecr describe-repositories
{
    "repositories": [
        {
            "registryId": "502858286805", 
            "repositoryName": "digital/core-addresses", 
            "repositoryArn": "arn:aws:ecr:eu-west-2:502858286805:repository/digital/core-addresses", 
            "createdAt": 1521132432.0, 
            "repositoryUri": "502858286805.dkr.ecr.eu-west-2.amazonaws.com/digital/core-addresses"
        }, 
...
        {
            "registryId": "502858286805", 
            "repositoryName": "digital/core-customers", 
            "repositoryArn": "arn:aws:ecr:eu-west-2:502858286805:repository/digital/core-customers", 
            "createdAt": 1521097940.0, 
            "repositoryUri": "502858286805.dkr.ecr.eu-west-2.amazonaws.com/digital/core-customers"
        }
    ]
}
```
## List just repository names
```
aws ecr describe-repositories | jq --raw-output '.repositories| map(.repositoryName) | .[]'
```
Example output
```
> aws ecr describe-repositories | jq --raw-output '.repositories| map(.repositoryName) | .[]'  
digital/bg-support-config
digital/core-addresses
digital/core-energysales
digital/support-config
digital/mock-home-move
digital/core-payments
digital/core-home-move
digital/core-content
digital/core-emails
digital/core-insurance
digital/core-campaigns
digital/bg-rabbitmq
digital/core-users
digital/repo
digital/core-questionnaires
digital/core-appointments
aws/codebuild/openjdk-6
digital/bg-java
digital/core-meters
digital/core-audits
digital/core-customers
digital/core-contact
digital/core-uaa
digital/core-energy
digital/core-home-services
```
## Create a repository
```
aws ecr create-repository --repository-name digital/<GRADLE project.name>
```
Example output
```
> aws ecr create-repository --repository-name digital/core-customers
{
    "repository": {
        "registryId": "502858286805", 
        "repositoryName": "digital/core-customers", 
        "repositoryArn": "arn:aws:ecr:eu-west-2:502858286805:repository/digital/core-customers", 
        "createdAt": 1521097940.0, 
        "repositoryUri": "502858286805.dkr.ecr.eu-west-2.amazonaws.com/digital/core-customers"
    }
}
```
## List all image tags for a particular repository
```
aws ecr describe-images --repository-name digital/<REPOSITORY_NAME> | jq --raw-output '.imageDetails | map(.imageTags) | sort | reverse| flatten | .[]'
```
Example output
```
> aws ecr describe-images --repository-name digital/core-customers | jq --raw-output '.imageDetails | map(.imageTags) | sort | reverse| flatten | .[]'
epic-feature-docker-build-79f0060f67381734021da927c74d5327256d29d3
epic-feature-docker-build-389fae096c948e438a939f70e72576da61784f7e
5.52.6
5.52.5
```
## Find the latest image tag for a particular repository
```
aws ecr describe-images --repository-name digital/<REPOSITORY_NAME> | jq --raw-output '.imageDetails | map(.imageTags) | sort | reverse | flatten | map(select(startswith("epic")|not)) | .[0]'
```
Example output
```
> aws ecr describe-images --repository-name digital/core-customers | jq --raw-output '.imageDetails | map(.imageTags) | sort | reverse | flatten | map(select(startswith("epic")|not)) | .[0]'
5.52.6