FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/checkin-1.0-SNAPSHOT.jar checkin.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","/checkin.jar"]

