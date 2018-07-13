This guide will get you a local [Kubernetes](https://kubernetes.io/docs/concepts/) cluster up and running using [Minikube](https://kubernetes.io/docs/getting-started-guides/minikube/)
# Prerequisites
* [Install Minikube ](https://kubernetes.io/docs/tasks/tools/install-minikube/)
* Install awscli
* Configure awscli using `> aws configure`
* Install python3
* Clone repos into a local working directory eg `~/bg/`
  * https://github.com/ConnectedHomes/bg-microservices-dev-environment
  * https://github.com/ConnectedHomes/bg-microservices-testing
  * https://github.com/ConnectedHomes/bg-core-uaa
  * https://github.com/ConnectedHomes/bg-core-customers
  * https://github.com/ConnectedHomes/bg-core-addresses
  * https://github.com/ConnectedHomes/bg-core-payments
  * https://github.com/ConnectedHomes/bg-core-emails
* [Create a Git Personal Access Token](https://github.com/settings/tokens)
* Configure credentials as environment variables
```
export DATABASE_PASSWORD=somepassword
export DATABASE_USERNAME=someusername
export GIT_USERNAME=yourgitusername
export GIT_PASSWORD=yourgitpat
```
* Configure minikube VM resources
  * By default minikube allocates 16GB RAM and 6 CPUs to the VM. If this is too much then edit `~/bg/bg-microservices-dev-environment/kubernetes/local/2_start_minikube.sh`
# Start up
* Execute startup scripts 
  * `> cd ~/bg/bg-microservices-dev-environment/kubernetes/local/`
  * `> 1_create_database_scripts.sh`
  * `> 2_start_minikube.sh`
  * `> 3_create-secrets.sh`
  * `> 5_create_microservices_k8s_config.sh`
  * `> 6_start_k8s.sh`
* Watch (with wide eyed wonder) as your system starts up
  * `> kubectl get pods -o wide`
# Test
## Simple Tests
* Execute the script `./quick-status.sh`:
```
➜  > ./quick-status.sh 
Support Config available on http://192.168.99.100:30298
Consul available on http://192.168.99.100:30494
Zuul available on http://192.168.99.100:32384
To open Kibana: > minikube addons enable efk; minikube addons open efk
http://192.168.99.100:30298/application/default
```
This will give you some simple links to open to verify your system is running
## Automated Tests
Run a basic test against your local cluser
```
> cd ~/bg/bg-microservices-testing
> src/scripts/run.sh basic local_kubernetes
```
