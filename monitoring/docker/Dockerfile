FROM java:8-jre
VOLUME /tmp
ADD  monitoring-1.0-SNAPSHOT.jar monitoring-1.0.jar
EXPOSE 8888
RUN sh -c 'touch /monitoring-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/monitoring-1.0.jar"]
