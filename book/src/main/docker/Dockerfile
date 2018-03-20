FROM java:8-jre
VOLUME /tmp
ADD  book-1.0-SNAPSHOT.jar book-1.0.jar
EXPOSE 8060
RUN sh -c 'touch /book-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/book-1.0.jar"]

