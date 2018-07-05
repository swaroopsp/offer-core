# Offer core

##The Requirement##
You are required to create a simple RESTful software service that will
allow a merchant to create a new simple offer. Offers, once created, may be
queried. After the period of time defined on the offer it should expire and
further requests to query the offer should reflect that somehow. Before an offer
has expired users may cancel it.

##Environment##
- Java 1.8
- Maven 3.3.3

### Build project ###
Install JAVA 8 and MAVEN 3

### Notes ###
In the project i have used lombok setters and getters and constructors automatic generation.

For more info on project lombok please refer to https://projectlombok.org/. 
Please refer to the link https://projectlombok.org/setup/maven to find the way to install the lombok.jar with maven.
Please refer to the link https://projectlombok.org/setup/eclipse to install lombok plugin in eclipse.

In the project i have used H2 in memory DB as database.

### Run project ###
Run OfferCoreApplication.java
or
mvn spring-boot:run 

### TDD ###
I have added an Integration test OfferResourceIntegrationTest.java that tests the API/resource. To run the integration test first run the project using above mentioned instructions[Run project]. 
And a unit test OfferTest.java

### CONCLUSION ###
There are quite a lot of improvements can be done in the project, but for the test purpose the effort so far is good enough.

