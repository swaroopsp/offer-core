If you have a new Mac before you do any setup first update your operating system (if you have a new enough version to be able to install Docker for Mac, then you don't have to update your OS). Go to ‘App Store’ app and select the update tab at the top, If you don't have the latest OSX operating system, you should see a prompt at the top. Do this before going to the next step.

Install Homebrew: open up your terminal window and run the following command to install homebrew.

`ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`

run the following command to make sure brew is correctly installed

`brew --version`

you should see the version of brew that you have installed.

Run ‘brew doctor’ and follow the instruction to fix all your brew environment issues.

`brew doctor`

once all the brew stuff is done, find out what version of python you have by running the following (Your mac will probably come with python 2 preinstalled but this is not suitable for development).

`python --version`

if the python is a version 3 then no need to install a newer version of python. if not then do the following to get the latest python version

`brew install python`

if all goes well, brew would have installed python and pip under the following directory.

`/usr/local/bin/`

this is the directory brew usually installs apps.
You will need to edit your $PATH so that the above directory takes precedence when looking for location of apps. Add the following to your bash_profile which should be located as stated below (if this doesn't exist then create it)

`~/.bash_profile`

add the following line to the file.

`export PATH=/usr/local/bin:$PATH`

run the following command to reload your bash profile

`source ~/.bash_profile`

find python install by running the command to narrow down the list of installed apps to just python related

`ls -la /usr/local/bin/ | grep python`

this should show you all the python related elements. As of the time I tried this myself, brew installed python as python3. The problem occurs with a lot of people in that running `python --version` returns version 2 but we need it to return version 3 (or what ever version is the latest as of when you tried this. i.e python[version_no]). running `python3 --version` does return version 3 though.

the following step is required only if running `python --version` doesn't return version 3 and above. you need to copy with the symbolic links the following, **/usr/local/bin/python3** to **/usr/local/bin/python**. run the following to do this.

`cp -a /usr/local/bin/python3 /usr/local/bin/python`

you will also need to do this for pip if you had to do this for python so run the following as well.

`cp -a /usr/local/bin/pip3 /usr/local/bin/pip`

after doing this, running `python --version` and `pip --version` should both return version 3 and above.

Next, setup you ssh key for github [Step 1](https://help.github.com/enterprise/2.12/user/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/) [Step 2](https://help.github.com/enterprise/2.12/user/articles/adding-a-new-ssh-key-to-your-github-account/)

Next install awscli with brew using the following command

`brew install awscli`

Install python aws library using the following

`pip install boto3`

Docker would be the next step that you need. You would want to go with the **Docker on Mac** option as this gives you less hassle (uses Mac OS Hypervisor meaning no need to install virtual box separately. You also wont need to install docker-compose separately either.)

[Install Docker](https://www.docker.com/get-docker)

from here on, you can follow the steps in the following [link](https://github.com/ConnectedHomes/bg-rest-api/wiki/New-Starters-Guide) to do the rest of your setup. You will see some overlap with some steps you have already done so just skip them. 

I would advice you to use brew to do your installs if you can help it as it makes your life really easy. a few key brew commands:

`brew --help` to show you some brew usages

`brew install [name]` install an app

`brew uninstall [name]` uninstall an app

You can find a list of home brew formulae (applications you can install with brew) [here](https://formulae.brew.sh/formula/)


