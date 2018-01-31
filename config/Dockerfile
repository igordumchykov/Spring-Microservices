FROM java:8-jre
VOLUME /tmp
ADD  target/server-config-1.0.jar server-config.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/server-config.jar"]