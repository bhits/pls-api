FROM tomcat:8-jre8
ADD server.xml /usr/local/tomcat/conf/
ENV AUTO_SCAN true
ENV SCAN_PERIOD 1 seconds
ENV MHC_PROPS /java/MHC_PROPS
ENV MHC_KEY 9HPcr8z634
ARG finalName
ADD $finalName /usr/local/tomcat/webapps/

