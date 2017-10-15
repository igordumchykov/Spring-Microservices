FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/search-1.0-SNAPSHOT.jar search.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/search.jar"]

