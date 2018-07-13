# What is a Monitor

Setting up a monitor across postman collections will let you run the collections periodically to check for its performance and response. 
The collection run frequency can be set as minimum as every 5 minutes, hourly, daily or Weekly basis.
Once you set up a monitor, Postman servers will hit the endpoints in your collection according to the specified frequency. You can also select a corresponding environment to use and store variables. 

# How to set up a Monitor

**1. From the Postman app**

Click on the ellipses (…) next to the collection you wish to monitor. Select “Monitor Collection” to open the MONITOR COLLECTION modal.
Enter a name for this monitor and choose a corresponding environment. Add an appropriate schedule for the monitor, and configure additional preferences. 
For example, you can run a monitor every 5 minutes or every Monday at 9:00 AM.

**2. From the Postman web**

Sign in with the Postman web view, and head over to the monitors page. Click the Add Monitor button.

# Additional preferences    

**Email notifications:**	     Get notifications about failures on up to 5 email addresses

**Request Timeout:**	             Specify a time interval after which your request is considered to time-out

**Delay between requests:**	     Add a time lag between subsequent requests

**Don’t follow redirects:**	     Disable following all redirects

**Disable SSL validation:**	     This disables validations performed on SSL certificates.


# Viewing monitor results

Once you set up monitors, you can receive daily and weekly emails with a summary for all your monitors. These email notifications can be turned off in the settings. 
In addition, you will receive important notifications (both in-app and email) in case a monitor fails.

# Sample Postman monitors set by the team:

https://bg-7451.postman.co/monitors?type=team

# Troubleshooting monitors

Monitors are kept in sync with your collections in the Postman app at all times. This means that you can debug in the app locally, while your monitors are updated on our servers, seamlessly.

The best way to debug monitors is via the Postman console available on the monitors web view. 
Click on the failed monitor, and review the relevant logs under the Console Log tab.

# Variable issues

Ensure that the same environment is used across local runs and monitor runs. 
You can confirm this by adding **console.log(environment);** to your request scripts and comparing the results across monitoring and local runs.
If your collection run depends on a global variable, change it to an environment variable. _Global variables are not supported in monitors at this time._

# Log relevant information

Often, issues come from unexpected response bodies or header values. You can log these with the following:
### console.log(JSON.stringify(responseBody, null, 2));
### console.log(JSON.stringify(responseHeaders, null, 2));