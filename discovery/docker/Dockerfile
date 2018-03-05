FROM java:8-jre
VOLUME /tmp
ADD  discovery-1.0-SNAPSHOT.jar discovery-1.0.jar
EXPOSE 8888
RUN sh -c 'touch /discovery-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/discovery-1.0.jar"]
