FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/eureka-server.jar"]

