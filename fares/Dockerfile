FROM java:8-jre
VOLUME /tmp
ADD  target/fares-1.0-SNAPSHOT.jar fares.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/fares.jar"]

