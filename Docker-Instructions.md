This guide will take you through running infrastructure services (redis etc) in Docker containers using Docker Compose as well as the microservices

## Setup Github Access
1. Set up your SSH key for GitHub [step 1](https://help.github.com/enterprise/2.12/user/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/) [step 2](https://help.github.com/enterprise/2.12/user/articles/adding-a-new-ssh-key-to-your-github-account/)
## Setup AWS CLI
1. [Install awscli](https://docs.aws.amazon.com/cli/latest/userguide/installing.html)
1. Ensure python3 is installed `> python3 -version`
1. Install python aws library `> pip3 install boto3`
## Setup Docker on Ubuntu
1. [Install Docker](https://www.docker.com/get-docker)
1. [Install Docker compose](https://docs.docker.com/compose/install/)
## Setup Docker on Mac
### Install
1. [Install VirtualBox](https://www.virtualbox.org/wiki/Downloads)
1. [Install Docker](https://www.docker.com/get-docker)
1. DO NOT START THE "Docker" application
> Docker For Mac provisions a Hyve VM which does not support routing from Host to VM containers. VirtualBox supports this so we don't need the "Docker" application running 
### Provision the VM
1. Open a Terminal
1. Provision a VM > `docker-machine create --driver virtualbox --virtualbox-cpu-count "4" --virtualbox-memory "10000" default`

> **NOTE** We have noticed that with only 10GB allocated you will not be able to run ALL services. You will only be able to run "basic" (see below)
3. Point your terminal at the VM > `eval $(docker-machine env default)`
4. Add port mappings. Execute the following in a Terminal
```
VBoxManage controlvm "default" natpf1 "tcp-port-1,tcp,,8888,,8888";
VBoxManage controlvm "default" natpf1 "tcp-port-2,tcp,,1521,,1521";
VBoxManage controlvm "default" natpf1 "tcp-port-3,tcp,,5672,,5672";
VBoxManage controlvm "default" natpf1 "tcp-port-4,tcp,,26379,,26379";
VBoxManage controlvm "default" natpf1 "tcp-port-5,tcp,,8500,,8500";
VBoxManage controlvm "default" natpf1 "tcp-port-6,tcp,,8771,,8771";
```

## Running the services

1. Clone the required github repositories. This process assumes a working directory of `~/bg`
```
mkdir ~/bg
cd ~/bg
git clone git@github.com:ConnectedHomes/bg-microservices-dev-environment.git
git clone git@github.com:ConnectedHomes/bg-microservices-dev-config.git
git clone git@github.com:ConnectedHomes/bg-microservices-testing.git
git clone git@github.com:ConnectedHomes/bg-core-uaa.git
git clone git@github.com:ConnectedHomes/bg-core-customers.git
git clone git@github.com:ConnectedHomes/bg-core-addresses.git
git clone git@github.com:ConnectedHomes/bg-core-payments.git
git clone git@github.com:ConnectedHomes/bg-core-audits.git
```
9. Switch to docker branches
```
cd ~/bg/bg-microservices-dev-config
git checkout docker
```
10. Set up optional command alias
```
alias dcbg="docker-compose --project-directory . -p bg -f output/docker-compose.yml"
```
11. Set key environment variables. Add the following to your ~/.bashrc or ~/.zshrc. Substitute appropriate values for user and password
```
export BASE64_ENCODED_AWS_ACCESSKEY=<BASE64 AWS_ACCESS_KEY_ID>
```
12. Configure awscli. Note you will have to obtain the credentials from somwhere (Andy)
```
cd ~/bg/bg-microservices-dev-environment/local
make aws_setup AWS_ACCESS_KEY_ID=<AWS_ACCESS_KEY_ID> AWS_SECRET_ACCESS_KEY=<AWS_SECRET_ACCESS_KEY>
```

13. Generate the necessary config files. Everything in [] is optional, if not provided it will use defaults e.g. GIT_CLONE_DIR=${PWD}/../../ as it is assumed you are 2 levels below your microservices directory.
```
cd ~/bg/bg-microservices-dev-environment/local/
make setup [ DATABASE_USERNAME=######## ] [ DATABASE_PASSWORD=######## ] [ GIT_CLONE_DIR=######## ]
```
14. Start the infrastructure services (Support Config). Everything in [] is optional. This will take a while (5 minutes) to start up. Defaults to infra.
```
cd ~/bg/bg-microservices-dev-environment/local/
make start [ what=infra|basic|all ]
```
OR START BASIC (Addresses and Customers)
```
cd ~/bg/bg-microservices-dev-environment/local/
make start what=basic
```
OR START EVERYTHING

> **NOTE** This will require more than 10GB allocated. It runs fine with 32GB on Ubuntu. 16GB might be fine but we don't have a Mac with enough RAM to test 16GB
```
cd ~/bg/bg-microservices-dev-environment/local/
make start what=all
```
15. Check the containers are all running
```
dcbg ps
```
or 
```
docker-compose --project-directory . -p bg -f output/docker-compose.yml ps
```
16. Verify services are running. Use Consul on http://localhost:8500. All should be green
17. You can now curl the addresses endpoint if basic or all is started
```
curl -H 'x-client-id: cbdcfa43-dd67-4c38-b418-83572a936fca' -H 'x-identity-zone: {"id": 1, "brand": "British Gas"}' -H 'x-security-context: {"client":"cbdcfa43-dd67-4c38-b418-83572a936fca","expiresAt":1478020513665,"expiresIn":3177,"scope":"read update","userContext":{"title":"Mr","firstName":"Energy","lastName":"Smart","ucrn":"003606931675","identityZoneId":1,"email":"ice008@bgdigitaltest.co.uk","profileType":"oam"}}' "http://localhost:8771/v1/addresses?postcode=E130HQ"
```
## Test using Postman
Once docker compose is showing all services as healthy you are in a position to run a smoke test postman collection.
```
cd ~/bg/bg-microservices-testing/
./src/scripts/run.sh basic local
```
Once complete you should have an html file generated at `build/newman/BasicMScollection.html` and this should show `Total Failures 0`
# Running Services in your IDE

1. Ensure all the application services are running > `make start what=all`
## Mac
1. Get the network range of the network > `docker network inspect bg_default` "IPAM.Config.Subnet"
1. Get the IP Address of the VM > `docker-machine ip`
1. Create a network route between your host and the docker network > `sudo route add 172.18.0.0/16 192.168.99.101`
## Replace the Docker microservice with your IDE microservice
1. Stop the service you wish to run in your IDE > `dcbg stop core_customers`
1. Start your service in your IDE

# Tidy up
## Mac
1. You can remove your route mapping > `sudo route delete 172.18.0.0/16 192.168.99.101`