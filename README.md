# Provider Lookup Service API

The Provider Lookup Service (PLS) API is responsible for storing provider information as a provider directory. The PLS also provides a RESTful API for querying providers by using several query parameters including *first name, last name, gender, address, and phone number* for individual providers, and *organization name, address, and phone number* for organizational providers.


## Build

### Prerequisites

+ [Oracle Java JDK 8 with Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
+ [Docker Engine](https://docs.docker.com/engine/installation/) (for building a Docker image from the project)

### Commands

This is a Maven project and requires [Apache Maven](https://maven.apache.org/) 3.3.3 or greater to build it. It is recommended to use the *Maven Wrapper* scripts provided with this project. *Maven Wrapper* requires an internet connection to download Maven and project dependencies for the very first build.

To build the project, navigate to the folder that contains the [**parent** `pom.xml` file](pls/pom.xml) using the terminal/command line.

+ To build a JAR:
    + For Windows, run `mvnw.cmd clean install`
    + For *nix systems, run `mvnw clean install`
+ To build a Docker Image (this will create an image with `bhits/pls:latest` tag):
    + For Windows, run `mvnw.cmd clean install & cd web & ..\mvnw.cmd clean package docker:build & cd..`
    + For *nix systems, run `mvnw clean install; cd ./web; ../mvnw clean package docker:build; cd ..`

## Run

### Prerequisites

This API uses *[MySQL](https://www.mysql.com/)* for persistence and *[Flyway](https://flywaydb.org/)* for database migration. It requires having a database user account with Object and DDL Rights to a schema with the default name `pls`. Please see [Configure](#configure) section for details of configuring the data source. 

A [SQL file](https://github.com/bhits/pls-api/blob/master/npi-db-sample/npi-db-sample.sql) is provided with this project to populate it with a small set of sample provider data.

### Commands

This is a [Spring Boot](https://projects.spring.io/spring-boot/) project and serves the API via an embedded Tomcat instance. Therefore, there is no need for a separate application server to run this service.
+ Run as a JAR file: `java -jar pls-x.x.x-SNAPSHOT.jar <additional program arguments>`
+ Run as a Docker Container: `docker run -d bhits/pls:latest <additional program arguments>`

*NOTE: In order for this API to fully function as a microservice in C2S Application, it is also required to setup the dependency microservices and support level infrastructure. Please refer to the [C2S Deployment Guide](https://github.com/bhits/consent2share/releases/download/2.1.0/c2s-deployment-guide.pdf) for instructions to setup the C2S infrastructure.*


## Configure

This API utilizes [`Configuration Server`](https://github.com/bhits/config-server) which is based on [Spring Cloud Config](https://github.com/spring-cloud/spring-cloud-config) to manage externalized configuration, which is stored in a `Configuration Data Git Repository`. We provide a [`Default Configuration Data Git Repository`]( https://github.com/bhits/c2s-config-data).

This API can run with the default configuration, which is targeted for a local development environment. Default configuration data is from three places: `bootstrap.yml`, `application.yml`, and the data which `Configuration Server` reads from `Configuration Data Git Repository`. Both `bootstrap.yml` and `application.yml` files are located in the `resources` folder of this source code.

We **recommend** overriding the configuration as needed in the `Configuration Data Git Repository`, which is used by the `Configuration Server`.

Also, please refer to [Spring Cloud Config Documentation](https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html) to see how the config server works, [Spring Boot Externalized Configuration](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) documentation to see how Spring Boot applies the order to load the properties, and [Spring Boot Common Properties](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html) documentation to see the common properties used by Spring Boot.

### Other Ways to Override Configuration

#### Override a Configuration Using Program Arguments While Running as a JAR:

+ `java -jar pls-x.x.x-SNAPSHOT.jar --server.port=80 --spring.datasource.password=strongpassword`

#### Override a Configuration Using Program Arguments While Running as a Docker Container:

+ `docker run -d bhits/pls:latest --server.port=80 --spring.datasource.password=strongpassword`

+ In a `docker-compose.yml`, this can be provided as shown below:
```yml
version: '2'
services:
...
  pls.c2s.com:
    image: "bhits/pls:latest"
    command: ["--server.port=80","--spring.datasource.password=strongpassword"]
...
```
*NOTE: Please note that these additional arguments will be appended to the default `ENTRYPOINT` specified in the `Dockerfile` unless the `ENTRYPOINT` is overridden.*

### Enable SSL

For simplicity in development and testing environments, SSL is **NOT** enabled by default configuration. SSL can easily be enabled following the examples below:

#### Enable SSL While Running as a JAR

+ `java -jar pls-x.x.x-SNAPSHOT.jar --spring.profiles.active=ssl --server.ssl.key-store=/path/to/ssl_keystore.keystore --server.ssl.key-store-password=strongkeystorepassword`

#### Enable SSL While Running as a Docker Container

+ `docker run -d -v "/path/on/dockerhost/ssl_keystore.keystore:/path/to/ssl_keystore.keystore" bhits/pls:latest --spring.profiles.active=ssl --server.ssl.key-store=/path/to/ssl_keystore.keystore --server.ssl.key-store-password=strongkeystorepassword`
+ In a `docker-compose.yml`, this can be provided as follows:
```yml
version: '2'
services:
...
  pls.c2s.com:
    image: "bhits/pls:latest"
    command: ["--spring.profiles.active=ssl","--server.ssl.key-store=/path/to/ssl_keystore.keystore", "--server.ssl.key-store-password=strongkeystorepassword"]
    volumes:
      - /path/on/dockerhost/ssl_keystore.keystore:/path/to/ssl_keystore.keystore
...
```

*NOTE: As seen in the examples above, `/path/to/ssl_keystore.keystore` is made available to the container via a volume mounted from the Docker host running this container.*

### Override Java CA Certificates Store In Docker Environment

Java has a default CA Certificates Store that allows it to trust well-known certificate authorities. For development and testing purposes, one might want to trust additional self-signed certificates. In order to override the default Java CA Certificates Store in a Docker container, one can mount a custom `cacerts` file over the default one in the Docker image as follows: `docker run -d -v "/path/on/dockerhost/to/custom/cacerts:/etc/ssl/certs/java/cacerts" bhits/pls:latest`

*NOTE: The `cacerts` references given in the volume mapping above are files, not directories.*

[//]: # (## API Documentation)
[//]: # (## Contribute)

## Contact

If you have any questions, comments, or concerns please see [Consent2Share](https://bhits.github.io/consent2share/) project site.

## Report Issues

Please use [GitHub Issues](https://github.com/bhits/pls-api/issues) page to report issues.

[//]: # (License)