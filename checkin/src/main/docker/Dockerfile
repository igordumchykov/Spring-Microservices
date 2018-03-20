FROM java:8-jre
VOLUME /tmp
ADD  checkin-1.0-SNAPSHOT.jar checkin-1.0.jar
EXPOSE 8070
RUN sh -c 'touch /checkin-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/checkin-1.0.jar"]

