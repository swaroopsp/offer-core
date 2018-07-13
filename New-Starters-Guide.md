## Systems Access
You need to obtain access to the following systems:

 1. Github
    1. Create an account if you don't have one already
    1. Add an SSH key from your new laptop to your github account [Follow link](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/)
    1. Enable 2 factor authentication for you account
    1. Install the DuoMobile mobile app to assist with this
    1. Email Dee (Dee.leahy@britishgas.co.uk) / Andy (Andy.Czerwinski@britishgas.co.uk with your github id. They will add you to the BG organisation
 1. Slack
    1. Email Dee / Andy with your personal email address and they will sort that out
    1. Install the desktop app if you wish
    1. Install the mobile app if you wish
 1. Apiary
    1. Email Andy with your personal email address and he will add you
 1. SVN
    1. Email Dee / Arvind for SVN account and project access.
 1. Bitbucket
    1. Email Dee for getting account created in Bitbucket and access for `Online API`

## Add JCE to Java
 1. [Follow this link](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)
 1. Accept the license agreement and download the jce_policy_8.zip file
 1. Expand the zip file
 1. Backup and replace you existing policy files

>     $ /usr/libexec/java_home -V
>     Matching Java Virtual Machines (2):
>         1.8.0_45, x86_64:	"Java SE 8"	/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
>         1.7.0_05, x86_64:	"Java SE 7"	/Library/Java/JavaVirtualMachines/1.7.0.jdk/Contents/Home
> 
>     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
>     $ cd /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/security/
>     $ cp local_policy.jar ~/backup/
>     $ cp US_export_policy.jar ~/backup/
>     $ sudo cp ~/Downloads/UnlimitedJCEPolicyJDK8/local_policy.jar .
>     $ sudo cp ~/Downloads/UnlimitedJCEPolicyJDK8/US_export_policy.jar .
## Install and configure Eclipse
1. Install Spring Tools from the Marketplace
1. Install Sonarlint from the Marketplace
1. Install Toad Extension from the Marketplace
1. Download and install Spotless Code Formatter
  * Save this file locally https://raw.githubusercontent.com/diffplug/spotless/master/spotless.eclipseformat.xml
  * In Preferences -> Code Formatting -> Install choose this file

## Install IntelliJ IDEA
 1. Download free latest version of IntelliJ - usually community edition from https://www.jetbrains.com/idea/download/#section=mac
 2. Install downloaded dmg or exe on your machine
 3. To open the project - 
 * Check-out your code to local machine or got to VCS menu and use "Checkout from Version Control -> Git Hub" option
 * To use the code on local machine - Go to "File -> Open -> <path to your local code repo>"
    
## Add Lombok to Eclipse
 1. Download lombok from https://projectlombok.org/download.html
 1. Run the downloaded lombok jar

    $ java -jar lombok.jar
 1. Choose the correct Eclipse location if it isn't detected automatically
 1. Restart Eclipse

## Clone & Build the code
There are various repos in github that you might need to work on. These are listed here:

https://github.com/ConnectedHomes/bg-rest-api/blob/master/design/online-micro-services-index.md

When cloning you should use SSH which will use the 2 factor authentication.

Build instructions are included in the specific repos

_Project build requires S3 keys to download the artefacts from S3 repo. Please ask team member to supply the details._

## Code Formatting
[This guide](Code-Formatting) will explain how the automated code formatting is integrated into the gradle build and how to run it in your IDE

## The config-repo
The config-repo is a spring cloud config set of configuration files that configure the microservices. They are available [here](https://github.com/ConnectedHomes/bg-microservices-dev-config). You should clone this repository. Never fork this repo and if you wish to make changes do so locally only. If you want to push changes to this repo make sure you raise a PR so the changes can be reviewed.
_**Caution - never upload this as public repo or expose it to out-side world in any form. Ensure all keys in the config are encrypted.**_
 
## Infrastructure Services
Install and use the VM with all the required services [link](https://github.com/ConnectedHomes/bg-rest-api/wiki/Development-VM-setup).

## Overview - Stage & Pre-Prod environments
We use HashiUI to view the state and logs for Stage and Pre-prod environments. This is available:
* (Stage)[https://193.67.160.70/nomad/global/cluster] 
* (Pre-prod)[https://193.67.160.161/nomad/global/cluster] 

## API Documentation
Our API Documentation is available [here](https://digital1.bgdigitaltest.co.uk/api-docs/index.html)

## Running our microservices
You can run our microservices through Kubernetes or Docker Compose
