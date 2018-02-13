FROM java:8-jre
VOLUME /tmp
ADD  target/prices-1.0-SNAPSHOT.jar prices.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/prices.jar"]

