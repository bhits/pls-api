
# Short Description
The Provider Lookup Service (PLS) API is responsible for storing provider information.

# Full Description

# Supported Tags and Respective `Dockerfile` Links

[`1.11.0`](https://github.com/bhits/pls-api/blob/master/pls/web/src/main/docker/Dockerfile),[`latest`](https://github.com/bhits/pls-api/blob/master/pls/web/src/main/docker/Dockerfile)[(1.11.0/Dockerfile)](https://github.com/bhits/pls-api/blob/master/pls/web/src/main/docker/Dockerfile)

For more information about this image, the source code, and its history, please see the [GitHub repository](https://github.com/bhits/pls-api).

# What is PLS?

The Provider Lookup Service (PLS) API is responsible for storing provider information as a provider directory. PLS also provides a RESTful API for querying providers by using several query parameters including *first name, last name, gender, address, and phone number* for individual providers, and *organization name, address, and phone number* for organizational providers.

For more information and related downloads for Consent2Share, please visit [Consent2Share](https://bhits.github.io/consent2share/).

# How to Use This Image

## Start a PLS Instance

Be sure to familiarize yourself with the repository's [README.md](https://github.com/bhits/pls-api) file before starting the instance.

`docker run  --name pls -e "CATALINA_OPTS=<additional configuration>" -d bhits/pls:latest`

*NOTE: In order for this API to fully function as a microservice in the Consent2Share application, it is required to setup the dependency microservices and support level infrastructure. Please refer to the [Consent2Share Deployment Guide](https://github.com/bhits/consent2share/releases/download/2.0.0/c2s-deployment-guide.pdf) for instructions to setup the Consent2Share infrastructure.*

## Configure

This API requires [pls-config.properties](https://github.com/bhits/pls-api/tree/master/config-template/pls-config.properties) and [pls-config-logback_included.xml](https://github.com/bhits/pls-api/tree/master/config-template/pls-config-logback_included.xml) to be mount in the container.
Default location in container is `/java/C2S_PROPS/pls-api/config-template/pls-config.properties` and `/java/C2S_PROPS/pls-api/config-template/pls-config-logback_included.xml`.

`docker run --name pls -v /path/on/dockerhost/pls-config.properties:/java/C2S_PROPS/pls-api/config-template/pls-config.properties -v /path/on/dockerhost/pls-config-logback_included.xml:/java/C2S_PROPS/pls-api/config-template/pls-config-logback_included.xml -d bhits/pls:latest`

## Environment Variables

When you start the PLS image, you can edit the configuration of the PLS instance by passing environment variables on the command line. 

### C2S_PROPS

This should be the location of root directory for externalized configuration. The default value is `/java/C2S_PROPS`.  PLS will try to load two configuration files 
`${C2S_PROPS}/pls-api/config-template/pls-config.properties` and `${C2S_PROPS}/pls-api/config-template/pls-config-logback_included.xml`.

This environment variable can be overridden by passing through `CATALINA_OPTS`. Make sure you put the configuration file under it.

`docker run --name pls -e CATALINA_OPTS="-DC2S_PROPS=/path/in/container" -v /path/on/dockerhost/pls-config.properties:/path/in/container/pls-api/config-template/pls-config.properties -v /path/on/dockerhost/pls-config-logback_included.xml:/path/in/container/pls-api/config-template/pls-config-logback_included.xml -d bhits/pls:latest`

### C2S_KEY

PLS supports Jasypt to decrypt the encrypted properties. `C2S_KEY` is used as a password for encryption. The encrypted properties should be wrapped in `ENC(...)` in the pls-config.properties file. There is no default value for `C2S_KEY`, so it must be provided.

`docker run --name pls -e CATALINA_OPTS="-DC2S_KEY=9HPcr8z634" -d bhits/pls:latest`

### AUTO_SCAN

This variable is used to configure [logback auto scan](http://logback.qos.ch/manual/configuration.html#autoScan) feature, so the expected value for this property is `true` or `false`. If `AUTO_SCAN=true`, logback will scan for changes in the included external configuration file and reconfigure itself when it detects a change. The default value is `true`.

`docker run --name pls -e CATALINA_OPTS="-DAUTO_SCAN=false" -d bhits/pls:latest`

### SCAN_PERIOD

This variable is used to configure [logback auto scan period](http://logback.qos.ch/manual/configuration.html#autoScan) configuration. If `SCAN_PERIOD=30 seconds`, logback will scan the external file for changes for every 30 seconds. The default value is `1 seconds`.

`docker run --name pls -e CATALINA_OPTS="-DSCAN_PERIOD=30 SECONDS" -d bhits/pls:latest`

# Supported Docker Versions

This image is officially supported on Docker version 1.12.1.

Support for older versions (down to 1.6) is provided on a best-effort basis.

Please see the [Docker installation documentation](https://docs.docker.com/engine/installation/) for details on how to upgrade your Docker daemon.

# License

View [license](https://github.com/bhits/pls-api/blob/master/LICENSE) information for the software contained in this image.

# User Feedback

## Documentation
 
Documentation for this image is stored in the [bhits/pls-api](https://github.com/bhits/pls-api) GitHub repository. Be sure to familiarize yourself with the repository's README.md file before attempting a pull request.

## Issues

If you have any problems with or questions about this image, please contact us through a [GitHub issue](https://github.com/bhits/pls-api/issues).

