# Objectives:
* Improve micro-services performance using a caching mechanism
* Provision of micro-service endpoint so clients can listen to server side events
* Provision of 24x7 ability for micro-services calls

# Acronyms used: 
* FEC - Front-end cache
* DB - Database
* SSE - Server Side Events

# Solution Design:

![](https://user-images.githubusercontent.com/9349198/36662465-6817d0c0-1ad6-11e8-8f0d-bf1bd96a2b8f.png)

# Applications involved:
* spring-cloud-gateway
* mongo db
* rabbit mq
* other required micro-services

# Limitations or points to be aware of:
* Data from cache could be stale - Refer to "Last-Modified" response header to find how stale the data is
* This feature has been enabled only for few end-points right now and only for GET operations - Please talk to dev-ops team to understand which endpoints are enabled to serve this feature
* Feature is available only for the logged-in users ie with Access Token
* As SSE allows, clients to open connections and server (at this moment) don't bother closing those connections or limiting number connections, there may be a fine tuning opportunity down the line. **One to be find out in load testing**
* Cache will be updated with the latest response only if the service returns "200" status code and ignores all other return codes

# Opt-in or Opt-out - How?

Clients can Opt-in or Opt-out for the feature. If Opt-in, client will get immediate response from Mongo DB. Latest response will be updated on Mongo DB in the background in Async manner and to the SSE endpoint if listened   

To opt-in add the request header `X-Fech-Enabled:true`. Missing this header or incorrect value will be considered as Opt-out.

# How to use Etag feature? 

When a client opt-in for FEC, the successful response should come with a response header - `Etag →_"0c659838e06c4aeffa14b03b33120fe2f"_`. Clients can cache the response on browser and on subsequent requests to the end-point for the same user, clients can send the Etag value as a response header - `If-None-Match:_0c659838e06c4aeffa14b03b33120fe2f_`.
When the response is not changed at the server, client will receive `304 - Not modified` response and when there is a change then the client will receive `200` response with new Etag. 

# How do we listen to SSE?

As per the design, for every requests, clients gets immediate (possibly stale) response from mongo cache. In the background, actual service call is made and the latest response is updated to mongo cache. These latest response will be sent on subsequent requests. SSE feature, allows the clients to get the latest response in the background when it gets updated in mongo cache by opening a dedicated connection with the server. To acheieve this, clients needs to do the below,
1. Generate a unique ID so the server can identify the client connection - `X-SSE-Token: `
2. Open a SSE connection - `/v1/transactions` by passing the unique ID in the header
3. Call the service endpoints - ex `/v1/customers`
4. Response gets sent to client from mongo cache immediately
5. Potentially after a few seconds delay, SSE connection will get latest response

# How can we enable/disable FEC feature for a specific endpoint? 

Enabling and disabling FEC feature for a specific end point is controlled via configuration file - `gateway-server.yml` 

To enable - add the filters `FECCacheAndPublishToFrontEnd & FECQueryCache`
To disable - remove the filters `FECCacheAndPublishToFrontEnd & FECQueryCache`

For example - to enable FEC feature for `/v1/addresses/`, refer to `filters:`- 

```
        - id: addresses-v1
          uri: lb://addresses-v1
          predicates:
            - Path=/v1/addresses/**
          filters:
          - FECCacheAndPublishToFrontEnd
          - FECQueryCache  
```

# Examples - 

* Open SSE connection - (ensure passing the unique token in the header)

`curl -X GET -H "X-Client-ID: " -H "X-SSE-Token:" "http://localhost:8887/v1/transactions/"`

* Get authentication token by passing correct username and password along with other header values - 

`curl -X POST -H "X-Client-ID: " -H "apikey: " -H "Content-Type: application/x-www-form-urlencoded" -H "Authorization: Basic " -H "Origin: " -H "Cache-Control: no-cache" -d 'grant_type=password&username=&password=' "https://193.67.160.70/uaa/oauth/token"`

* Call customers end-point by passing required header values

`curl -X GET -H "x-client-id: " -H "Authorization: Bearer " -H "X-Fech-Enabled: true" -H "If-None-Match: " -H "Cache-Control: no-cache" -H "X-SSE-Token:" "https://193.67.160.70/v1/customers/"`

# Useful Mongo DB commands:
* show dbs - displays all schemas
* use bg - switch to a schema called "bg" 
* show collections - display all tables
* db.cacheResponse.find() - To fetch all records from a specific table/collection
* db.cacheResponse.find({"_id" : "123456-1520266808126"}) - To fetch a specific record from a specific table/collection
* db.cacheResponse.drop() - To delete a table/collection
