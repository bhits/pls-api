@ECHO off

:: ********************************Please set the following configuration Path************************
:: NOTE: DO NOT change variable names
:: Following value of variables will get from jenkins
:: SET ftps_server_host=
:: SET transfer_key=
:: SET cipher_username=
:: SET cipher_password=
:: Set configuration path for target directory
SET target_directory=%CONFIGS_DELIVERY_HOME%\%JOB_NAME%
:: ***************************************************************************************************
:: Declare variables start
:: Set log name for configurations uploading information
SET log_file_name=%date:~4,2%-%date:~7,2%-%date:~10,4%
SET log_file_save_path=%target_directory%\%log_file_name%
:: Declare variables end

:: Start running script
CALL :DECRYPT_ACCOUNT_AUTHENTICATION
CALL :UPLOAD_CONFIGS_TO_DESTINATION
CALL :CHECK_UPLOADING_STATUS
EXIT

:: Declare methods start
:: Create configs save path if it is not existing.
:DECRYPT_ACCOUNT_AUTHENTICATION
  FOR /F %%i IN ('"ECHO %cipher_username% | openssl enc -d -aes-256-cbc -a -salt -pass pass:%transfer_key%"') DO SET plain_username=%%i
  FOR /F %%i IN ('"ECHO %cipher_password% | openssl enc -d -aes-256-cbc -a -salt -pass pass:%transfer_key%"') DO SET plain_password=%%i
  GOTO :EOF

:UPLOAD_CONFIGS_TO_DESTINATION
  ::SET specifies_the_file=/COPYALL /B /SEC /MIR *.properties
  SET specifies_the_file=*.properties
  SET upload_options=--trace-ascii %log_file_save_path%.log -u %plain_username%:%plain_password%
  CURL -k -T %specifies_the_file% %upload_options% %ftps_server_host%
  GOTO :EOF
  
:CHECK_UPLOADING_STATUS
  FOR /f %%f IN ('findstr /c:"We are completely uploaded and fine" "%log_file_save_path%.log"') DO SET/a totalSuccess+=1
  ECHO The number of successfully uploading is: %totalSuccess% > %target_directory%\uploadingStatus_%log_file_name%.log
  ECHO CURL ERROR CODE: %ERRORLEVEL% >>%target_directory%\uploadingStatus_%log_file_name%.log
  GOTO :EOF
:: Declare methods end