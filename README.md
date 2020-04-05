This repository is set up to demostrate a possible bug in Payara server 5.201 https://github.com/payara/Payara/issues/4596. Roles are not assigned correctly in certificate realm. 

Steps to reproduce: 

- [x] Build the root pom.xml

- [x] Build the docker image: ```sh sudo docker build -t payara-clientcert-test2 .

- [x] Run the docker image: ```sh sudo docker run --rm -p 8181:8181 --name pt payara-clientcert-test2

- [x] Step into the container: ```sh sudo docker exec -it pt /bin/bash

- [x] In the container, create a pkcs12 file: ```sh keytool -importkeystore -srckeystore appserver/glassfish/domains/production/config/keystore.jks -destkeystore export.p12 -deststoretype PKCS12 -srcalias s1as -deststorepass changeit
The requested password is "changeit"

- [x] Exit the container

- [x] Copy the key off the container: ```sh sudo docker cp pt:/opt/payara/export.p12 .

- [x] Run: ```sh curl -v -k --cert-type P12 --cert export.p12:changeit --header "Content-Type: text/xml;charset=UTF-8" --data @soap.xml https://localhost:8181/adminservice

The outcome is "Hello from CN=localhost,OU=Payara,O=Payara Foundation,L=Great Malvern,ST=Worcestershire,C=UK as admin: **false**"

The expected outcome is "Hello from CN=localhost,OU=Payara,O=Payara Foundation,L=Great Malvern,ST=Worcestershire,C=UK as admin: **true**" 

