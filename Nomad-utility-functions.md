## Commands 
**nomad-al**

          This prints the allocation id
          e.g. nomad-al meters

**nomad-log**

          This calls nomad-al to get the allocation id and runs the nomad logs
          e.g. nomad-log meters | more

**nomad-run**

          It restarts the microservice.
          nomad-run <hcl-file-name> <micro-service-version>
          e.g.
          Default
            nomad-run meters
          This will run meters with the version specified in ~/nomad-jobs/meters.hcl
                                               
          Specific Version
            nomad-run meters  1.10.3  
          This will modify the update the hcl file (~/nomad-jobs/meter.hcl) with version 1.10.3 and then run meters.

          Latest Version (lt)
            nomad-run meters lt
            This will modify the update the hcl file (~/nomad-jobs/meter.hcl) with the latest version that is available on 10.224.70.49 and then run meters.

          If either hcl file or jar file does not exist, it will error out
          It also keeps the backup of last hcl file in the same folder with name meters.hcl.bak