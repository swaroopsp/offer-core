The latest version of Docker CE frowns on command line entered passwords for performing a `docker login`

# Ubuntu
You can configure `pass` to store docker credentials. 

1. Install [pass](https://www.passwordstore.org/) 
```
$ sudo apt-get install pass
```
2. Install the [credentials helper for pass](https://github.com/docker/docker-credential-helpers/releases)
3. Extract credentials helper and add to path
```
$ tar xf docker-credential-pass-v0.6.0-amd64.tar.gz
$ sudo mv docker-credential-pass /usr/local/bin/
```
4. Ensure you have gpg2 installed
```
$ sudo apt-get install gnupg2 -y
```
5. Generate a public GPG key
```
$ gpg2 --gen-key                           
gpg (GnuPG) 2.1.11; Copyright (C) 2016 Free Software Foundation, Inc.
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

Note: Use "gpg2 --full-gen-key" for a full featured key generation dialog.

You need a user ID to identify your key; the software constructs the user ID
from the Real Name, Comment and Email Address in this form:
    "Heinrich Heine (Der Dichter) <heinrichh@duesseldorf.de>"

Real name: Paul Hopkins
E-mail address: paul.hopkins@centrica.com
You selected this USER-ID:
    "Paul Hopkins <paul.hopkins@centrica.com>"

Change (N)ame, (E)mail, or (O)kay/(Q)uit? O
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilise the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilise the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.
gpg: key 1559E8AF marked as ultimately trusted
gpg: directory '/home/paulhopkins/.gnupg/openpgp-revocs.d' created
gpg: revocation certificate stored as '/home/paulhopkins/.gnupg/openpgp-revocs.d/68AB34AB9245C17A5738C62E5BA4A9651559E8AF.rev'
public and secret key created and signed.

gpg: checking the trustdb
gpg: marginals needed: 3  completes needed: 1  trust model: PGP
gpg: depth: 0  valid:   1  signed:   0  trust: 0-, 0q, 0n, 0m, 0f, 1u
pub   rsa2048/1559E8AF 2018-04-12 [S]
      Key fingerprint = 68AB 34AB 9245 C17A 5738  C62E 5BA4 A965 1559 E8AF
uid         [ultimate] Paul Hopkins <paul.hopkins@centrica.com>
sub   rsa2048/90EB3962 2018-04-12 []
```
6. Initialise pass
```
$ pass init paul.hopkins@centrica.com      
mkdir: created directory '/home/paulhopkins/.password-store/'
Password store initialized for paul.hopkins@centrica.com
```
7. Perform a docker login (*NOTE* You will be prompted for your passphrase)
```
$ $(aws ecr get-login --no-include-email --region eu-west-2)
WARNING! Using --password via the CLI is insecure. Use --password-stdin.
Login Succeeded
```