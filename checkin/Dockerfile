FROM java:8-jre
VOLUME /tmp
ADD  target/checkin-1.0-SNAPSHOT.jar checkin.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","/checkin.jar"]

