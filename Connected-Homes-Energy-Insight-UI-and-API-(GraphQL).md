CH tech team has created a status page where you can see the availability of key parts of our stack with SLAs stated where applies:

[Status Page](https://monitoring.core.myenergy.bgchprod.info/status)

The three main parts are:
1. Production APIs (platform APIs that feed the NGA app)
2. Production UI (this is the current my energy UI )
3. Sandbox (the test environment and the support tool)

This environment is a copy of our production systems with a small test data set loaded.

This sandbox environment contains a copy of our support tools and interactive API, this tool is currently somewhat of a work in progress and we intend to improve upon it.  The URL for now is a little be cumbersome, I hope to improve that in the near future:

    https://ui.sandbox.core.myenergy.bgchprod.info/me-support/

To login use the credentials:

    Username: bg.user
    Password: M3t3ring

Once in the support tool, you can get basic information about an account and login to the interactive API with those account details, some example account numbers are:

    G: 851007375475, E: 851007375475
    G: 850021997620, E: 850022797176

On the interactive API section you are able to explore our GraphQL API and execute requests against the sandbox.  I'm in the process of working with our documentation team to writes some guides to our API.
I appreciate that our GraphQL API has a steep learning curve, any queries no matter how minor please direct to me and I'll do my best to find the answers.

To use our GraphQL API outside of the interactive environment you will need to first obtain an API session token.  This is done by calling our eligibility endpoint with a pair of account numbers (an Electricity and Gas account number, providing only one for single fuel customers).  Our eligibility API will only provide access tokens for customer whom our API supports.  This covers a few business rules, currently we don't support: economy 7, pre-pay or smart customers with less that 7 days of data, and a few more.

You can find documentation for the eligibility API in our support application:

    https://ui.sandbox.core.myenergy.bgchprod.info/me-support/api-inspector

You can obtain an API session token by calling the eligibility API, this takes the account numbers and returns the token, as follows:

    curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization:FtxV3xQxqUSbhqfOWjzawB9jLeFsmiSV9om2FCdEqPRDZbFXdUx/XECjH7kt2j8wD0XfNbTUsx90ytjzIPYUDg==" \
    -d '{ "elec_account_id": "851007375475", "gas_account_id":"851007375475" }' \
    https://api.sandbox.core.myenergy.bgchprod.info/eligibility

    {
        "data": {
            "token":"ZmE3OTU2MWQtOWY0YS00YWM3LWE3Y2QtZDExNDRlNGVmMDNjOmQ1ZGU2ODMyLWUxYWQtNDlhNi1hYjAzLThiZTVlOGE2MDJhMQ=="
        }
    }

Note the eligibility API is a privileged API, authenticated with a shared secret.  It is intended to be called from your backend systems with the resulting API token being passed to the frontend client.  I hope to add API key management to our support tools.  For now valid API keys in the sandbox environment are:

    FtxV3xQxqUSbhqfOWjzawB9jLeFsmiSV9om2FCdEqPRDZbFXdUx/XECjH7kt2j8wD0XfNbTUsx90ytjzIPYUDg==

Once you have an API session token you can use the GraphQL API, for example:

    curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: ZmE3OTU2MWQtOWY0YS00YWM3LWE3Y2QtZDExNDRlNGVmMDNjOmQ1ZGU2ODMyLWUxYWQtNDlhNi1hYjAzLThiZTVlOGE2MDJhMQ=="
\
    -d '{ "query": "query {\n  consumptionDates {\n    gas {\n from\n      to\n    },\n    electricity {\n      from\n     
 to\n }\n  }\n}", "variables": null }' \
    https://api.sandbox.core.myenergy.bgchprod.info/graphql

    {
        "data": {
            "consumptionDates": {
                "gas": [
                    {
                        "from": "2016-09-29T00:00:00.000Z",
                        "to": "2017-05-23T23:59:59.999Z"
                    }
                ],
                "electricity": [
                    {
                        "from": "2016-10-20T00:00:00.000Z",
                        "to": "2017-05-23T23:59:59.999Z"
                    }
                ]
            }
        }
    }

To use our GraphQL API effectively I suggest you look at GraphQL specific clients, we currently use Apollo: http://dev.apollodata.com/ .

