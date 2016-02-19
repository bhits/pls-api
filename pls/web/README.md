# 1. Externalizing and Encrypting

## 1.1. Externalization

A PropertyTemplate folder has been created which mimics our application specific properties e.g, providers.proerties.
Below is the structure of <b>pls</b> under <b>\pls-api\config-template</b>. All developers should be using this template for any changes in these application specific properties.

		config-template
						*.properties

#### Setting Up System Property Variables (Ignore this step if variables are already defined for another project)
Create two new System property variables in catalina.properties under {CATALINA_HOME} or Pivotal “Servers” in STS:

		MHC_KEY=9HPcr8z634

		MHC_PROPS='\\workspaces\\pls-api\config-template



NOTES:

	1.	Path of MHC_PROPS must point to PropertyTemplate directory of your current workspace branch.

	2.	Restart STS to pick up newly created System Property variables.



## 1.2.	Encryption

While adding a new variable in application specific properties e.g, providers.proerties or creating a new properties file from scratch, ensure that all the sensitive information is encrypted using Jasypt.

#### Using Jasypt
An open source product called Java Simplified Encryption (JASYPT) is used to replace clear text passwords in files with encrypted strings that are decrypted at run time. Encrypting credentials Jasypt provides a command line utility that can be used to encrypt the values of your properties.

Download the Jasypt distribution and unpack it. The utilities reside in the bin directory.

	C:\Users\himalay.majumdar\Downloads\jasypt-1.9.2\bin>encrypt input=admin password=9HPcr8z634
	----ENVIRONMENT-----------------
	Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.25-b02
	----ARGUMENTS-------------------
	input: admin
	password: 9HPcr8z634
	----OUTPUT----------------------
	VSWiYdKWUgxQGzQw7WjEAA==

Update your properties file(under PropertyTemplate folder) putting the out inside ENC().

Example:

	database.password=ENC(VSWiYdKWUgxQGzQw7WjEAA==)
--------------------------------------------------------------------------------------------------
# 2. Logback Configuration Externalization Instructions

## 2.1. Quick Start

There are two ways to test whether the logger is working. Please follow the steps below to quickly startup the application. For details and customization, please see the following topics.

### Option One

Running in Java IDE

1. Added two system property variables in catalina.properties under {CATALINA_HOME} or Pivotal “Servers” in STS
 
   - AUTO_SCAN=true

   - SCAN_PERIOD=30 seconds          

2. Start pls-web server
3. Go to the directory `\pls-api\config-template\provider-web-config.properties`
3. Open the file `instrumentation.properties`
4. Get the value of `instrumentationKey` 
5. Go to the (local)site `https://localhost:8080/pls/instrumentation/loggerCheck?instrumentationKey=`
6. Paste the value of `instrumentationKey` in the end of above url.
7. The page will show currently logger information

Note: After changing logging behavior, you can refresh application web page to let Test method pick up logging behavior immediately

