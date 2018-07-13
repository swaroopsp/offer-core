The FITNESS Test Infrastructure ; an initiative to automatically test the BG micro-services with full business journey scenarios.

Here is the dashboard, the report from the latest overnight journey tests run (preprod and stage).

http://193.67.160.9/fitness_testruns/preprod/latest/report.html

http://193.67.160.9/fitness_testruns/stage/latest/report.html

```



```

**Table of contents**

* [Summary](#summary) 
* [How to run FITNESS ](#howtorun) 
* [How to analyse a FITNESS report](#howtoanalyse) 
* [How to add new tests ](#howtoadd) 
* [Future Improvements](#futureimprovements) 
* [Running Fitness Test on 49](#runfitness)


<a name="summary"></a> 
# Summary

FITNESS is about running a set of full business journey tests, each of them involving hitting multiple microservices on our Digital1 environment.

For example the ‘chi_happypath’ test involves the address, customer and appointment micro-services.

It is built around POSTMAN collections/environments and the RUNMAN test runner, plus a fair amount of custom pre-processing and post-processing.

It can be used by different people and in different contexts: developers, testers, managers, daily cron, post-merge/CI-dashboard update, etc. 

It is run on 49 so there is access to internal endpoints not exposed through zuul, which makes possible the creation of more complex test sequences.

It is one of many tools we can use to improve testing, visibility of our team work, development process, etc. It is not intended at replacing unit tests, extensive QA testing, mock integrated tests, etc. It is there to complete all this.  

<a name="howtorun"></a> 
# How to run FITNESS 

## Pre-requisites
You will need to install Newman following [these instructions](https://github.com/postmanlabs/newman#getting-started) in the target environment that will host and run the Fitness tests.  

Our main dashboard test suite (a single test per business flow our team supports) should run automatically everyday.

The tool can also be used locally to run a subset of those tests with a different version of our MS or a completely different test suites. Here is typically how: 

```
#clone the repository and move into the right directory
git clone https://github.com/ConnectedHomes/bg-fitness.git
cd ~/git/bg-fitness

#build and make the jar executable
gradle build
chmod aou+rwx build/libs/bg-fitness-0.1.0.jar 

#run by passing at least an input and output folder argument
java -jar ./build/libs/bg-fitness-0.1.0.jar --input ./src/main/resources/tests --output ../fitness_testruns/fitness_testrun_`date +%Y-%m-%dT%H:%M:%S`
```

More info about what arguments are available:

```
Option (* = required)                   Description                
---------------------                   -----------                
--help [String: print this help]                                   
* --input <File: the directory containing all the tests to be run>                              
* --output <File: the folder where all  (default: ./fitness_report) reports will be created>                                         
--variable [String: Pass a global variable value, will override the values from individual test environments]    
```

<a name="howtoanalyse"></a> 
# How to analyse a FITNESS report 

The main report page provides a high level centralised result page including the overall success rate, duration and outcome of each flow test. 

![](https://raw.githubusercontent.com/ConnectedHomes/bg-rest-api/master/reference/fitness/fitness.png?token=AMk_HCU6lMrezrxvkanSlrfSkrqks_9Qks5Y24rbwA%3D%3D)

Note the gym animation depends on the success rate.

Then for each test, you can drill down by clicking on 'report' to see all the rest calls it made and how it went (response code, post call JS tests, etc). 

If you want absolutely everything, you can click on 'more' to see full request and response including bodies and headers, this is all in a json-file which is not too user-friendly for now. If there is more demand, it will be exposed in a nicer UI. 

<a name="howtoadd"></a> 
# How to add new tests 

To add a new test that will result in a new row in our daily generated report, proceed as follow:
1. Create a collection in postman and make sure it runs properly in the postman runner
2. Save the collection as a test.json file and the environment as env.json file 
3. Create a folder with a meaningful name in here https://github.com/ConnectedHomes/bg-fitness/tree/master/src/main/resources/tests and commit your 2 files (using our commit conventions, like 'bugfix/newtest' as branch name). 


That's it, your test will be run. 

But there is a little more to it:
* make sure your rest calls URLs relies on a variable called 'core_url'. This variable should be in your environment and will be overriden automatically with something like https://10.224.70.49/
* For certain tests, you may want to use a random postcode or today's date, you can have special function calls like this ($preTestUtils.getXXX()) inside your env.json files instead of a static value that wouldn't work for a recuring test:

```
    "values": [
      {
        "enabled": true,
        "key": "core_url",
        "value": "https://193.67.163.82/",
        "type": "text"
      },
      {
        "enabled": true,
        "key": "postcode",
        "value": "$preTestUtils.getRandomPostcode()",
        "type": "text"
      },
      {
        "enabled": true,
        "key": "today",
        "value": "$preTestUtils.getCurrentDateTime('yyyy-MM-dd')",
        "type": "text"
      }
    ],
```

Then you can use {{today}} or {{postcode}} in your collection calls wherever convenient (URL, headers, tests, etc)

Important: before committing, lets make sure the whole sequence run successfully in postman runner using {{core_url}} everywhere and meaningful tests in each request of the sequence.

Please use {{uaa_url}} for any calls to uaa and make sure when committing, the value is 'https://localhost:8443/'.

Pleas split test sequence in a meaningful way. If one  part is anonymous and one part isn't, they should typically be two separate tests. 

Test folders should have meaningful names, with no spaces!

To add a time delay between two rest requests of a sequence, you can use the following in the 'tests' part of request A:
``` 
function pausecomp(millis)
 {
  var date = new Date();
  var curDate = null;
  do { curDate = new Date(); }
  while(curDate-date < millis);
}
//will wait 10 seconds
pausecomp(10000);
```

<a name="futureimprovements"></a> 
# Future improvements 

* Add more tests, providing an honest dashboard of all the business flows we support and how they are currently performing. Adding internal endpoints when they are required to reset the data allowing daily meaningful tests with the same customer. Currently missing are:
   * home-move-lead - need to delete sap sales-order
   * Rewards - we already have that 
   * Service Orders - need to delete service orders in SAP
   * Appointment Cards - need to delete appointments online
   * POST Customers - New data need to be entered in the request body to generate new UCRN

* Journeys added in Fitness are as below:
  * Energy Orders
  * HIVE Contract
  * Reset Password Request
  * CHI Appointments
  * Premise Creation
  * Surveys
  * Support Option and Categories
  * Rewards

* Journeys in progress and to be added in Fitness : 
  * Payments
  * Service Accounts
  * Direct Debit
  * Orders

* Provide a more user-friendly access to full resquest and response bodies
* Run the flow tests in parallel instead of running them sequentially as it is now 
* More verbose central report with meaningful stats etc. 
* Add more variables injection like for uaa_url
* Add unit tests and validation 
* Serve the report as a JSON file that can be used like an API offerring integration in Slack and the like. 

---------------------------------------------------------------
# Git steps for testers

when it comes to git convention, here is a reminder for testers

1. first create a new branch called bugfix/newtest
2. go to src/main/resources/tests
3. click on new file
4. use the / key to create subfolders
5. create your new file as TESTCATEGORY / TESTNAME / test.json
6. copy and paste the content of the collection file you exported from postman
7. in the same folder, create env.json and copy the content of the env file you exported from postman as well (change uaa_url value to https://localhost:8443) and save
8. create a pull request to merge back into master
9. add reviewers if relevant
10. click merge 
11. delete the bugfix/newtest branch

that's it, it will be picked up automatically 

---------------------------------------------------------------
<a name="runfitness"></a> 
# Running Fitness Test on 49

Steps:

Login to server using details as below,
 
1. 10.224.70.49 ,
   User : custadm
   Pwd : To be obtained if required
 
2. switch to root user and type the same password again,
   Command to be used :su
 
3. check the latest version is available,
   Command to be used  :ls from_s3/bg-fitness
 
4. kickstart the test,
   Command to be used  :sh fitness/bin/run_fitness.sh

---------------------------------------------------------------




