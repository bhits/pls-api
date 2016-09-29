FROM tomcat:8-jre8
ENV AUTO_SCAN true
ENV SCAN_PERIOD 1 seconds
ENV C2S_PROPS /java/C2S_PROPS
ARG finalName
COPY $finalName /usr/local/tomcat/webapps/
