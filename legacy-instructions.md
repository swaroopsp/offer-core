If you want to install all the infrastructure services locally:

1) Ask yourself why!
2) Don't
3) If you still want to here are some instructions


#### Redis
##### Native install
Installation Instructions 

1. On OSX use brew to install the services.

    brew install redis

2. Redis service can be started using the following command

    brew services start redis

3. Redis-sentinel can be started using the following command

    redis_install_dir/bin/redis-sentinel /usr/local/etc/redis-sentinel.conf --sentinel


Execution Instructions on Linux.

    xterm -T "Redis Master" -e redis-server &
    xterm -T "Redis Sentinel" -e redis-sentinel ~/Downloads/redis-3.2.4/sentinel.conf &

##### Docker
** UNTESTED **

    docker run --name some-redis -dp 6379:6379 redis
### Oracle
#### Native install
Instructions TBD
#### Docker
Instructions TBD
### Rabbit-MQ
#### Native install
Instructions TBD
#### Docker
** UNTESTED **

    docker run -d --hostname my-rabbit --name some-rabbit -p 4369:4369 rabbitmq:3
### Consul
#### Native install
[See Consul local installation guide](https://github.com/ConnectedHomes/bg-rest-api/wiki/Guide-for-using-Consul-discovery-locally)
#### Docker
Instructions TBD
### Vault
#### Native install
Instructions TBD
#### Docker
** UNTESTED **

    docker run --cap-add=IPC_LOCK --name=dev-vault -dp 8200:8200 vault

## Start the Services
 The first service generally required to be running is the https://github.com/ConnectedHomes/bg-support-config service
## Verify that each service is responding
### Redis Master

    redis-cli ping

### Redis Sentinel

    wget -S --spider http://localhost:26379

### Oracle
### Rabbit-MQ
### Consul
### Vault
### bg-support-config

     curl http://localhost:8888/v1/health

### Mock Addresses
### bg-core-addressess

    curl localhost:8080/management/health