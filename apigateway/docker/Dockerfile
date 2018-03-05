FROM java:8-jre
VOLUME /tmp
ADD  apigateway-1.0-SNAPSHOT.jar apigateway-1.0.jar
EXPOSE 8095
RUN sh -c 'touch /apigateway-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/apigateway-1.0.jar"]
