A caching strategy has been implemented to cache responses in the API Gateway layer (currently Zuul).

## Cache Behaviour

### Normal Operation (cache enabled, no cached value available)
Client -> Zuul -> Downstream Service -> Zuul -> add to cache -> Client

### Normal Operation (cache enabled, cached value available)
Client -> Zuul -> get from cache -> Client

### Normal Operation (cache disabled)
Client -> Zuul -> Downstream Service -> Zuul -> Client

## Cache-Control

A response from a cache enabled resource will return the following headers, with values set appropriately:
```
Cache-Control:private, max-age=120
Last-Modified: Mon, 03 Jan 2011 17:45:57 GMT
```
A request to a cache enabled service can set the following header to retrieve a fresh value and not use the cached version. This will also update the cache for subsequent calls
```
Cache-Control:no-cache
```
A request can set the max-age value and this will retrieve a cached version if it is available within this time period, set the Cache-Control to a short TTL and  it will request it from the downstream service to cache for subsequent calls. The Client will then make a subsequent call after the TTL expires which should retrieve the newly cached data
```
Cache-Control: max-age=<seconds>
```
![Cache Control Flow](https://github.com/ConnectedHomes/bg-rest-api/blob/master/images/Flow%20With%20Cache%20Control.png)

## Caching is dumb
This cache is intentionally dumb. It caches based on the URL requested. If parameter order changes for example, it will not recognise it as being cached and cache it again.

## Configuration
The following shows the 2 configuration options available:
```
zuul:
  cachePassThrough: false
  routes:
    addresses-v1: 
      path: /v1/addresses/**
      stripPrefix: false
      cache: true
```
### cache
This configures, on a per route basis, whether the responses should be cached.

### cachePassThrough
The specifies that any route configured to cache should always pass the request on even when there is a cached value available.

## Outstanding work
 * There is currently no endpoint available to flush cache entries. This can be done though through the redis-cli.
 * Cache-Control behaviour needs to be implemented
 * We need to strip out a unique request parameter that the FE adds. Without doing that every request would be unique and the caching would be irrelevant.