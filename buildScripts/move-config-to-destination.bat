@ECHO off

:: ********************************Please set the following configuration Path************************
:: NOTE: DO NOT change variable names
:: Jenkins will set PROPS_HOME environment variable if this batch is running in a Jenkins job
:: INITIAL_PROJECT_VERSION get from jenkins
:: INITIAL_CONFIG_NAME get from jenkins
:: CONFIGS_DELIVERY_HOME get from jenkins
:: JOB_NAME get from jenkins
:: SET PROPS_HOME=
:: SET INITIAL_GIT_BRANCH=
:: SET INITIAL_PROJECT_VERSION=
:: SET INITIAL_CONFIG_NAME=
:: SET CONFIGS_DELIVERY_HOME=
:: SET JOB_NAME=
:: ***************************************************************************************************

:: Get running branch name
CALL :GETBRANCH_NAME

:: Declare variables start
:: Set configuration path for target config
SET target_config_path=%PROPS_HOME%\%branch_name%\%INITIAL_PROJECT_VERSION%
:: Set name of target config
SET config_name=%INITIAL_CONFIG_NAME%
:: Set configuration path for destination
SET destination=%CONFIGS_DELIVERY_HOME%\%JOB_NAME%
:: Declare variables end

:: Start running script
CALL :COPY_CONFIG_TO_DESTINATION

EXIT %ERRORLEVEL%

:: Declare methods start
:GETBRANCH_NAME
  IF DEFINED INITIAL_GIT_BRANCH (
 	  ECHO INITIAL_GIT_BRANCH is defined 
 	  SET branch_name=%INITIAL_GIT_BRANCH%
  ) ELSE (
 	  ECHO INITIAL_GIT_BRANCH is NOT defined
 	  FOR /f %%i IN ('"git rev-parse --abbrev-ref HEAD"') DO SET branch_name=%%i
  )
  GOTO :EOF 
  
:COPY_CONFIG_TO_DESTINATION
  ::SET specifies_the_file=/COPYALL /B /SEC /MIR *.properties
  SET specifies_the_file=/MIR /XX %config_name%.properties
  SET copy_options=/R:3 /W:5 /LOG:%destination%\RoboLog.log /NS /NC /NDL
  ROBOCOPY %target_config_path% %destination% %specifies_the_file% %copy_options% >NUL
  SET/A errlev="%ERRORLEVEL% & 24"
  IF %errlev% NEQ 0 (
	  ECHO Delivery %config_name% Failed!
	  EXIT %errlev%
  )
  SET ERRORLEVEL=%errlev%
  GOTO :EOF
  
:: Declare methods end