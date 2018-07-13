## Sonar for developers
### Intellij
[SonarLint](http://www.sonarlint.org/) is the preferred linter for applying sonar code quality code checks.  The project's website includes links and information on downloading a suitable IDE plugin.
To fetch the Sonar rules currently in use on our projects provide the URL when setting up an IDE plugin
> https://centricaqube.webhop.me/

Provide your developer github credentials to generate an authentication token. 
  
## Sonar rules discussion

Rules xml -> https://github.com/ConnectedHomes/bg-rest-api/blob/master/sonarqube/rules.xml

1. Should we use separators like _ or - in package names?
   Sonar Qube says no.
   But **_customers.ws.get_customer_details.response_** looks more readable than
   _**customers.ws.getcustomerdetails.response**_.


2.  
  ```Java
    public JerseyConfig() {
      register(Customers.class);
   ```
Remove this call from a constructor to the overridable "register" method.
This is listed as a critical issue.
I don't think it is critical or even an issue in our context.
Because JerseyConfig is just a configuration class and doesn't have any business logic in it.
It becomes an issue when you have business logic where behaviour is overridden by subclasses.
For each resource resgitered inside each micro-service this would be listed as a critical issue.