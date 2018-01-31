FROM java:8-jre
VOLUME /tmp
ADD  target/webface-1.0-SNAPSHOT.jar webface.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","/webface.jar"]