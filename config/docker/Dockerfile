FROM java:8-jre
VOLUME /tmp
ADD  config-1.0-SNAPSHOT.jar config-1.0.jar
EXPOSE 8888
RUN sh -c 'touch /config-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/config-1.0.jar"]
