FROM tomcat:8-jre8
ADD server.xml /usr/local/tomcat/conf/
ENV AUTO_SCAN true
ENV SCAN_PERIOD 1 seconds
ENV C2S_PROPS /java/C2S_PROPS
ENV C2S_KEY 9HPcr8z634
ARG finalName
ADD $finalName /usr/local/tomcat/webapps/

