This repository is set up to demostrate a possible bug in Payara server 5.194. Client certificate authentication does not work correctly if the default realm "certificate" is used.

Steps to reproduce: 

1. Build the root pom.xml

2. Build the docker image: sudo docker build -t payara-clientcert-test2 .

3. Run the docker image: sudo docker run --rm -p 8181:8181 --name pt payara-clientcert-test2

4. Step into the container: sudo docker exec -it pt /bin/bash

5. In the container, create a pkcs12 file: keytool -importkeystore -srckeystore appserver/glassfish/domains/production/config/keystore.jks -destkeystore export.p12 -deststoretype PKCS12 -srcalias s1as -deststorepass changeit
The requested password is "changeit"

6. Exit the container

7. Copy the key off the container: sudo docker cp pt:/opt/payara/export.p12 .

8. Run: curl -v -k --cert-type P12 --cert export.p12:changeit --header "Content-Type: text/xml;charset=UTF-8" --data @soap.xml https://localhost:8181/adminservice

The outcome is "Hello from CN=localhost,OU=Payara,O=Payara Foundation,L=Great Malvern,ST=Worcestershire,C=UK as admin: false"

The expected outcome is "Hello from CN=localhost,OU=Payara,O=Payara Foundation,L=Great Malvern,ST=Worcestershire,C=UK as admin: true" 

