# Provider Lookup Service API

The Provider Lookup Service (PLS) API is responsible for storing provider information as a provider directory. PLS also provides a RESTful API for querying providers by using several query parameters including *first name, last name, gender, address, and phone number* for individual providers, and *organization name, address, and phone number* for organizational providers.


## Build

### Prerequisites

+ [Oracle Java JDK 8 with Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
+ [Docker Engine](https://docs.docker.com/engine/installation/) (for building a Docker image from the project)

### Commands

This is a Maven project and requires [Apache Maven](https://maven.apache.org/) 3.3.3 or greater to build it. It is recommended to use the *Maven Wrapper* scripts provided with this project. *Maven Wrapper* requires internet connection to download Maven and project dependencies for the very first build.

To build the project, navigate to the folder that contains the [**parent** `pom.xml` file](pls/pom.xml) using terminal/command line.

+ To build a JAR:
    + For Windows, run `mvnw.cmd clean install`
    + For *nix systems, run `mvnw clean install`
+ To build a Docker Image (this will create an image with `bhits/pls:latest` tag):
    + For Windows, run `mvnw.cmd clean install & cd web & ..\mvnw.cmd clean package docker:build & cd..`
    + For *nix systems, run `mvnw clean install; cd ./web; ../mvnw clean package docker:build; cd ..`

## Run

### Prerequisites

This API uses *[MySQL](https://www.mysql.com/)* for persistence. It requires having a database user account with Object Rights to a schema with the default name `npi`. *Please see [Configure](#configure) section for details of configuring the data source.*

Currently, the PLS API does not support a database migration process, so the schema must be created manually. A [SQL file](npi-db-sample/npi-db-sample.sql) is provided with this project to create the schema and populate it with a small set of sample provider data.

This API is a [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html) project that requires a separate application server to run it. [Apache Tomcat 8](http://tomcat.apache.org/) is the recommended application server to run this API. The expected default context path for this API is `/pls`.

### Deployment

For easy deployment:

1. Find the `war` file located in `pls/web/target` folder after building the project
2. Rename the file to `pls.war` if it has a different name
3. Copy `pls.war` to Tomcat's `webapps` folder
4. Configure Tomcat and PLS properties *(See [Configure](#configure) section)*
5. Start up Tomcat

Please refer to [Tomcat Web Application Deployment](http://tomcat.apache.org/tomcat-8.0-doc/deployer-howto.html) documentation for more details about Tomcat deployment.

## Configure

This API supports externalized configuration, but it expects certain environment variable to be available in Tomcat. Please navigate to `$TOMCAT_HOME/conf/catalina.properties` and add the following variables:

+ `C2S_PROPS`: This should be the location of root directory for externalized configuration. If `C2S_PROPS=/c2s-config`, PLS will try to load these external configuration files:
	+ `/c2s-config/pls-api/config-template/pls-config.properties`: Contains configuration for data source, Cross-origin Resource Sharing (CORS), and Instrumentation Key for checking logging level. Please see the [sample properties file](config-template/pls-config.properties).
	+ `/c2s-config/pls-api/config-template/pls-config-logback_included.xml`: External [logback](http://logback.qos.ch/) file that will be included into the application logback configuration. This file can be used to configure logging details including where the log files will be stored and logging level. Please see the [sample included logback file](config-template/pls-config-logback_included.xml).

+ `C2S_KEY`: PLS uses [Jasypt](http://www.jasypt.org/) to decrypt the encrypted properties. `C2S_KEY` is used as a password for encryption. The encrypted properties should be wrapped in `ENC(...)` in the `pls-config.properties` file. It is still allowed to use plain text property values as usual.
	+ Example: `database.password=ENC(VSWiYdKWUgxQGzQw7WjEAA==)`
+ `AUTO_SCAN`: This variable is used to configure [logback auto scan](http://logback.qos.ch/manual/configuration.html#autoScan) feature, so the expected value for this property is `true` or `false`. If `AUTO_SCAN=true`, logback will scan for changes in the included external configuration file and reconfigure itself when it detects a change.
+ `SCAN_PERIOD`: This variable is used to configure [logback auto scan period](http://logback.qos.ch/manual/configuration.html#autoScan) configuration. If `SCAN_PERIOD=30 seconds`, logback will scan the external file for changes for every 30 seconds.

### Encrypt Property Values Using Jasypt

Jasypt provides a command line utility that can be used to encrypt the property values. Please refer to [Jasypt](http://www.jasypt.org/) documentation for details.

Example for Windows:
```bat
C:\jasypt-1.9.2-dist\jasypt-1.9.2\bin>encrypt.bat input=admin password=strongpassword

----ENVIRONMENT-----------------
Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.71-b15
----ARGUMENTS-------------------
input: admin
password: strongpassword
----OUTPUT----------------------
hm7aAgkyntvhoT6NGR5E1A==
```

Example for \*nix Systems:

```bash
root@test /jasypt-1.9.2-dist/jasypt-1.9.2/bin
$ encrypt.sh input=admin password=strongpassword

----ENVIRONMENT-----------------
Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.71-b15
----ARGUMENTS-------------------
input: admin
password: strongpassword
----OUTPUT----------------------
jqVmrSh0UeHwQcrA/qZOdg==
```

### Provide Environment Variables While Running as a Docker Container

+ `docker run -d -e CATALINA_OPTS="-DC2S_PROPS=/java/C2S_PROPS -DC2S_KEY=strongpassword -DAUTO_SCAN=true -DSCAN_PERIOD=60 seconds" -v "/path/to/config/root/on/dockerhost:/java/C2S_PROPS" bhits/pls:latest`
+ In a `docker-compose.yml`, this can be provided as:

```yml
version: '2'
services:
...
  pls.c2s.com:
    image: "bhits/pls:latest"
    environment:
      CATALINA_OPTS: "-DC2S_PROPS=/java/C2S_PROPS -DC2S_KEY=strongpassword -DAUTO_SCAN=true -DSCAN_PERIOD=60 seconds"
    volumes:
      - /path/to/config/root/on/dockerhost:/java/C2S_PROPS
...
```

### Enable SSL

Please refer to [Apache Tomcat 8 SSL/TLS Configuration HOW-TO](https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html) documentation for configuring SSL on Tomcat.

In Docker environment, `$TOMCAT_HOME/conf/server.xml` can be overridden by mounting a volume as `"/path/on/dockerhost/server.xml:/usr/local/tomcat/conf/server.xml"`. The mounted `server.xml` file can refer to a keystore inside the container that can be separately mounted like `"/path/on/dockerhost/ssl_keystore.keystore:/ssl_keystore.keystore"`.

In a `docker-compose.yml`, this can be provided as:

```yml
version: '2'
services:
...
  pls.c2s.com:
    image: "bhits/pls:latest"
    volumes:
      - /path/on/dockerhost/server.xml:/usr/local/tomcat/conf/server.xml
      - /path/on/dockerhost/ssl_keystore.keystore:/ssl_keystore.keystore
...
```

*Example `server.xml` Snippet with `keystore` Configuration:*
```xml
...
<!-- Define a SSL Coyote HTTP/1.1 Connector on port 8443 -->
<Connector
           protocol="org.apache.coyote.http11.Http11NioProtocol"
           port="8443" maxThreads="200"
           scheme="https" secure="true" SSLEnabled="true"
           keystoreFile="/ssl_keystore.keystore" keystorePass="changeit"
           clientAuth="false" sslProtocol="TLS"/>
...
```

### Override Java CA Certificates Store In Docker Environment

Java has a default CA Certificates Store that allows it to trust well-known certificate authorities. For development and testing purposes, one might want to trust additional self-signed certificates. In order to override the default Java CA Certificates Store in a Docker container, one can mount a custom `cacerts` file over the default one in the Docker image as `docker run -d -v "/path/on/dockerhost/to/custom/cacerts:/etc/ssl/certs/java/cacerts" bhits/pls:latest`

*NOTE: The `cacerts` references given in the both sides of volume mapping above are files, not directories.*

[//]: # (## API Documentation)

## Notes

The current code base of this API is based on a legacy implementation, so it uses relatively older technologies compared to the other Consent2Share (C2S) microservices and it does not follow the same coding styles and implementation guidelines. The BHITS Development Team is planning to modernize this API by a complete redesign and rewrite of it as a [Spring Boot](https://projects.spring.io/spring-boot/) project for better integration with C2S infrastructure.

[//]: # (## Contribute)

## Contact

If you have any questions, comments, or concerns please see [Consent2Share](https://bhits.github.io/consent2share/) project site.

## Report Issues

Please use [GitHub Issues](https://github.com/bhits/pls-api/issues) page to report issues.

[//]: # (License)