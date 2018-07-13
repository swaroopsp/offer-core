# Dev Env Setup with Individual Services & Softwares On MacBook

## I.	Required Software To Install:
### 1.	Consul v 0.8.0 (recommended)
### 2.	RabbitMQ
### 3.	Kitematic
### 4.	Docker
### 5.	Redis & Redis Sentinel

## II.	Configuration
### 1.	Checkout projects
### 2.	Download config-repo
### 3.	YML files changes

## III.	Basic set of Required Projects (list is not exhaustive):
### 1.	Start support-config
### 2.	Start rabbitmq
### 3.	Start core-uaa
### 4.	Start support-zuul
### 5.	Start support-customers
### 6.	Verify Running/Failing Services on Consul UI
### 7.	Creating Data in Oracle Database


***
## I.	Required Software - Installation Guide:

_**First Step:**_
Update brew, so it updates itself with all latest formulae (software!).

**> $ brew update**

**NOW INSTALL REQUIRED SOFTWARES:**

### 1. Install Consul:

**Step 1:** Download 0.8.0 (this is recommended version):
	https://releases.hashicorp.com/consul/0.8.0/consul_0.8.0_darwin_amd64.zip

**Step 2:** Start, Stop Service Instructions, follow instructions on this web page:

	https://www.consul.io/intro/getting-started/agent.html 

	Note: You may have to grant execution permissions with chmod to consul.
	
_**Then, run this: > $ ./consul agent -dev**_

**Step 3:** After Consul service is started, browse it's UI at: http://localhost:8500/ui 

Sample Consul UI:

![Consul UI](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/Consul-UI2.png)

Note: you can change its port!

### 2. Install RabbitMQ:

**Step 1:** **brew install rabbitmq**

**Step 2:** RabbitMQ server scripts are installed into /usr/local/sbin. This is not automatically added to your path, so you may wish to add PATH=$PATH:/usr/local/sbin to your .bash_profile or .profile.

**Step 3:** Start Server: _`brew services start rabbitmq`_

**Step 4:** Stop Server: _`brew services stop rabbitmq`_

### 3. Install Kitematic (UI for Docker)

**Step 1:** Press Command+Space and type Terminal and press enter/return key.
Run in Terminal app: 

_**ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)" < /dev/null 2> /dev/null ; brew install caskroom/cask/brew-cask 2> /dev/null **_

and press enter/return key. Wait for the command to finish.

**Step 2:** `> $ brew cask install kitematic`
Done! You can now use Kitematic.

### 4. Install Docker

**Step 1:** Go to: www.docker.com, create an account with your BG email address.

**Step 2:** Verify email received and your account is then confirmed.

**Step 3:** Install Docker from: https://download.docker.com/mac/edge/Docker.dmg 

**Step 4:** Then on top right hand side, you'll find Docker icon.

**Step 5:** Click on it and sign in into your account. Click on Kitematic (Screenshot below)

![Docker Context Menu](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/Docker-ContextMenu.png)

**Step 6:** Search for oracle-xe-11g from sath89 & select repository (see highlighted in below screenshot) and click on CREATE. 

![Docker Oracle XE 11g](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/Docker-OracleXE11g.png)

**Step 7:** Docker then starts downloading this and after download, this will appear in your Docket Dashboard from where you and either start or stop service and also can launch a terminal.

![Docker Downloading Oracle XE 11g](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/Docker-OracleXE11g-Downloading.png)

**Step 8:** Click on Start icon on the window, and it should start up like below (on those lines..):

![Docker Starting Oracle XE 11g](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/Docker-OracleXE11g-Starting.png)

**Step 9:** After this, Click on EXEC icon on the window and this open up a terminal.

Run highlighted commands as shown in the below screenshot. 
Enter user-name as _**system**_
Enter password as _**oracle**_

![SQL Login Commandline](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/SqlLogin.png)

**Step 10:** Run this query below to make sure everything is ok!
_**SQL> select 1 from dual;**_

_If you get response 1 then this confirms Docker Oracle Instance is running ok!_

**Step 11:** You may install SQL Developer from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

### 5. Install Redis (also installs Redis Sentinel)

**Step 1:** Downlod Redis from here: http://download.redis.io/releases/redis-4.0.8.tar.gz 
Note: This is the latest version available as on 28th Feb 2018.

**Step 2:** Unzip the downloaded tar.gz and open up a terminal and CD into that directory.

**Step 3:** run command:**$ make**

Note: All sources are in C files, so to build a binary executable, we need to compile.

**Step 4:** run command: **cd /src**

**Step 5:** run command: **$ ./redis-server &**

**Step 6:** You should see something like this on the console. Sample screenshot below. If this is the case then all done! Redis is ready and it can accept requests to keep them in cache!

![Redis Running](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/Redis-Running.png)

**Step 7:** run command: $ ./redis-server ../sentinel.conf --sentinel &
You should see something like this on the console.

![Redis Sentinel Running](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/Redis-Sentinel-Running.png)

***

## II. Configuration
* **Checkout projects**
  * _**bg-support-config:**_ checkout the project from https://github.com/ConnectedHomes/bg-support-config
  * _**core-uaa:**_ checkout the project from https://github.com/ConnectedHomes/bg-core-uaa 
  * _**support-zuul:**_ checkout the project from https://github.com/ConnectedHomes/bg-support-zuul
  * _**support-customers:**_ checkout the project from https://github.com/ConnectedHomes/bg-core-customers 

*  **Download config-repo.zip and unzip it and save this into a directory.** (This is supplied separately. Ask!)
  
* **YML Files Changes**

  * **Open application.yml** from the config-repo (see above - after you've downloaded and unzipped!)
    * Change Port number, username and password for Oracle configuration (note these from Docker Oracle) - See screenshot.

![application.yml changes](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/appplication-yml-changes.png)

_**Refer to Docker Oracle Settings:**_
![Docker Oracle Port Number Reference](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/docker-oracle-port-number.png)

  * **Open bootstrap.yml** from the bg-support-config. (Refer to line # 26 & 27 - server git url & searchPaths)
    * url will be the absolute path to your config-repo and searchPath should be set to: '*,v*'

![Bootstrap YML file Changes](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/bootstrap-yml-changes.png)


## III.	Basic set of Required Projects (list is not exhaustive):

  1. **Start support-config**. This is the first service to be started! Browse http://localhost:8888/addresses-v1/default 

![Starting Support Config](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/starting-support-config.png)

  2. **Start rabbitmq**

  3. **Start core-uaa** and browse: http://localhost:8888/uaa/development 

![Starting Core UAA](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/starting-core-uaa.png)
  
  4. **Start support-zuul**

  5. **Start support-customers**

  6. **Verify Running/Failing Services on Consul UI**, browse: http://localhost:8500/ui 

![Consul Running/Failing Services](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/consul-running-failing-services.png)

  7. **Creating Data in Oracle DB**: As required, copy content from respective .sql files in relevant projects and execute them in SQL. As you'd need data for the bug fix or feature that you may work on.

![DB Scripts](https://github.com/ConnectedHomes/bg-rest-api/blob/master/DevSetupForMacBook/images/db-scripts.png)

***
