1. Raise a PR (two types of PR’s 1. Bugfix, 2. Feature) this PR should be from either bugfix to epic or feature to epic.


2. Wait for Travis build to finish on the respective PR that is raised by you. And once finished add reviewers names on the PR’s (see the top right part of PR page to select names)

3. Set the label to “ready to merge” on the PR page

4. This would auto notify micro-prs channel about your PR that it is ready to be reviewed and merged if reviewers are happy with your PR (the changes you made to the code)

5. Once they approve, this means, a new build would happen on travis. 

6. Go to: respective projects’s github home page, click on commits tab, in the dropdown select your epic or master, and then click on the tick icon to go to respective travis build’s page copy the url on the address bar and ping devops team to tell to deploy them to stage

7. Once devops confirm that it has been deployed, tell Testers that ticket is ready for testing

8. Once Testers test the ticket and happy then you’d need to raise another PR to merge your epic branch to master. and ping this PR’s link to tester who has tested your ticket and they will add their comment as tested and merge it to master then wait for travis build to finish and then follow above mentioned process to go to travis build page and ping that url to devops to deploy master version to pre-prod.

NOTE: Please talk to dev-ops or ms leads if they want to deploy only epic version to preprod or master version.
in 90% of cases they only want to deploy epic versions. or bugfix versions. Once these are fully tested only then raise another PR to merge them to master. as per above process once preprod testing has been confirmed by the testers move this card to ready to go live and get this added to the release bucket and track your ticket to get it released in the near release date.

Action: You are responsible to keep tracking the ticket’s process and progress
from Dev and prod