FROM java:8-jre
VOLUME /tmp
ADD  target/book-1.0-SNAPSHOT.jar book.jar
EXPOSE 8060
ENTRYPOINT ["java","-jar","/book.jar"]

