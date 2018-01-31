FROM java:8-jre
VOLUME /tmp
ADD  target/search-apigateway-1.0-SNAPSHOT.jar search-apigateway.jar
EXPOSE 8095
ENTRYPOINT ["java","-jar","/search-apigateway.jar"]

