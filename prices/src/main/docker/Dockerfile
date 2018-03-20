FROM java:8-jre
VOLUME /tmp
ADD  prices-1.0-SNAPSHOT.jar prices-1.0.jar
EXPOSE 8080
RUN sh -c 'touch /prices-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/prices-1.0.jar"]
