# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM payara/server-full:5.201

COPY --chown=payara:payara payara-clientcert-test2-ear/target/payara-clientcert-test2-ear-0.9-SNAPSHOT.ear ${DEPLOY_DIR}
COPY --chown=payara:payara bin/pre-boot-commands.asadmin ${PREBOOT_COMMANDS}
COPY --chown=payara:payara bin/post-boot-commands.asadmin ${POSTBOOT_COMMANDS}