FROM java:8-jre
VOLUME /tmp
ADD  webface-1.0-SNAPSHOT.jar webface:1.0-SNAPSHOT.jar
EXPOSE 8001
RUN sh -c 'touch /webface:1.0-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/webface:1.0-SNAPSHOT.jar"]
