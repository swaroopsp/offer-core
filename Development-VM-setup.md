## DEPRECATED
Use [Kubernetes](https://github.com/ConnectedHomes/bg-rest-api/wiki/Development-setup-with-Kubernetes)

Development VM have the following tools required for running micro-services on local system.

* Consul
* redis 
* redis + sentinel
* rabbitmq
* oracle express

Following default ports are exposed by services on VM

 Service | Port | Link 
---|---|---
consul | 8500 | [http://localhost:8500/](http://localhost:8500/)
redis | 6379 | [http://localhost: 6379/](http://localhost:6379/)
redis-sentinel|26379 | [http://localhost:26379/](http://localhost:26379/)
rabbit MQ | 25672 | [http://localhost:15672/](http://localhost:15672/)
oracle express | 1521 | **connection string:** jdbc:oracle:thin:@localhost:1521:xe,  **user:** vagrant, **password:** please ask




## Pre-req
#### Install Oracle VirtualBox and Vagrant

Follow the instructions on [virtualbox.org](https://www.virtualbox.org/wiki/Downloads) for download and install.


Follow the instructions on [vagrantup](https://www.vagrantup.com/docs/installation/) for download and install.
	
<strong>NOTE:</strong> you will need to have Virtualisation enabled in the BIOS for this to work. For BG linux laptops you should contact Frank Symonds in IS Express (in Orbis) who has the BIOS password to enable you to change this.

#### Steps to setup VM
1. Clone the dev environment repo https://github.com/ConnectedHomes/bg-microservices-dev-environment.  (This project contains the __vagrant_share__ directory referenced by the Vagrantfile created by `vagrant init`).
1. Clone the dev config repo https://github.com/ConnectedHomes/bg-microservices-dev-config.  (This project contains the __config_repo__ directory referenced by the Vagrantfile created by `vagrant init`).
1. Open the terminal window or command prompt, and execute the following commands.
1. Create the directory for vagrant environment

		mkdir bg_env
		cd bg_env
		
1. Execute the following commands.

		vagrant init paulalanhopkins/centos-dev
1. Edit the generated Vagrantfile and replace the contents with the following. <strong>NOTE:</strong>Modify the following paths: `bg-microservices-dev-environment`, `bg-microservices-dev-config`, `bg-core-uaa/src/main/resources/db`, `bg-core-customers/src/main/resources/db`:
```
Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  config.vm.box = "paulalanhopkins/centos-dev"

  config.vm.network "forwarded_port", guest: 4647, host: 4647, auto_correct: true
  # Redis
  config.vm.network "forwarded_port", guest: 6379, host: 6379, auto_correct: true
  config.vm.network "forwarded_port", guest: 26379, host: 26379, auto_correct: true
  # RabbitMQ
  config.vm.network "forwarded_port", guest: 5672, host: 5672, auto_correct: true
  config.vm.network "forwarded_port", guest: 15672, host: 15672, auto_correct: true
  config.vm.network "forwarded_port", guest: 4269, host: 4269, auto_correct: true
  # Consul
  config.vm.network "forwarded_port", guest: 8500, host: 8500, auto_correct: true
  # Oracle
  config.vm.network "forwarded_port", guest: 1521, host: 1521, auto_correct: true
  config.vm.network "forwarded_port", guest: 8079, host: 8079, auto_correct: true
  # Support Config
  config.vm.network "forwarded_port", guest: 8888, host: 8888
  # UAA
  config.vm.network "forwarded_port", guest: 9001, host: 9001
  # Mock UAA
  config.vm.network "forwarded_port", guest: 7000, host: 7000
  # Zuul
  config.vm.network "forwarded_port", guest: 8771, host: 8771

  config.vm.synced_folder "/path/to/bg-microservices-dev-environment/vagrant_share", "/vagrant_share"
  config.vm.synced_folder "/path/to/bg-microservices-dev-config", "/config_repo"
  # Mount database directories
  config.vm.synced_folder "/path/to/bg-core-uaa/src/main/resources/db", "/sql/0_uaa"
  config.vm.synced_folder "/path/to/bg-core-customers/src/main/resources/db", "/sql/1_customers"

  config.vm.provider "virtualbox" do |vb|
    vb.name = "centos-dev"
    # Customize the amount of memory on the VM:
    vb.memory = "4096"
    vb.cpus = 4
  end
end
```
1. Start the server. `vagrant up` (you can stop it when needed using `vagrant halt`)
 * NOTE: If it complains about different versions of Guest Additions then let it install the latest version and then do a `vagrant halt` followed by another `vagrant up` and it should be fine.

#### Set up the box and run some services
1. SSH into the box
``` 
 > vagrant ssh
 > cd /vagrant_share/
```
2. Initialise the database
``` 
 > ./1_run_ddl.sh
```
3. Configure the AWSCLI
```
 > ./2_configure_aws.sh YOUR_AWS_ACCESS_KEY YOUR_AWS_SECRET_KEY
```
4.Download the latest microservices jars
 * NOTE: You can do this as often as you like. 
```
 > ./3_download_microservices.sh
```
5. Start the microservices and supporting applications in Nomad
```
 > ./4_nomad_start_microservices.sh
```
6. Stop the microservices and supporting applications
```
 > ./5_nomad_stop_microservices.sh
```
### Useful Nomad Commands
 * `nomad status` To list all nomad jobs
 * `nomad status microservices` List the status of a particular job. This will list allocation ids
 * `nomad alloc-status ALLOC_ID [TASK_NAME]` To list allocation status of a particular job. If there are more than one task then you will need to add the task name
 * `nomad logs [-f] ALLOC_ID [TASK_NAME]` To show (and optionally follow) log entries for a nomad task
 * `nomad fs ALLOC_ID [TASK_NAME]` To show the file system for a nomad task

## Known issues  / Changes requested to Vagrant Box
Please include:
* Any issues you find here with the box that require resolving in the next box release
* Any features you would like included
* Missing ~/README documentation that you think would be useful

1. When [Consul](http://localhost:8500/) url is accessed, 404 or 500 error appears.

> Please follow these steps
> 
> 1. SSH to VM using `vagrant ssh` command.
> 2. Stop consul service. `sudo service consul stop`
> 3. Start consul service. `sudo service consul start`
> 4. Disconnect from VM. `ctrl+d` or `exit`.

2. Redis + Sentinel services not accessible from host system.

> 1. SSH to VM using `vagrant ssh` command.
> 2. Edit the Redis config file. `sudo vi /etc/redis.conf`
> 3. Stop redis service. `sudo service redis stop`
> 4. Start redis service. `sudo service redis start`
> 5. Disconnect from VM. `ctrl+d` or `exit`.

3. <THIS SHOULD NOW WORK IN 1.2> [Rabbitmq web console](http://localhost:15672/) is not accessible.

> To access the rabbitmq web console, we need to start the service in vagrant
>  ```
>  vagrant ssh
>  sudo rabbitmq-plugins enable rabbitmq_management
>  sudo service rabbitmq-server stop
>  sudo service rabbitmq-server start
>  exit from vagrant ssh.
>  In browser try the link. (http://localhost:15672/)
> ```
  
4. README doesn't include the sysdba oracle login and password details (pw is `vagrant`)

5. README should be displayed when user SSH's into the box

6. Delayed Exchange Plugin (Not required in all the cases but better to have it enabled by default)

   Steps -> https://github.com/ConnectedHomes/bg-rest-api/issues/400#issuecomment-367784886

