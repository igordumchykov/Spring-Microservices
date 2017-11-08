FROM java:8-jre
VOLUME /tmp
ADD  target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/eureka-server.jar"]

#FROM alpine:latest
#MAINTAINER Jason Wilder <mail@jasonwilder.com>
#
#RUN apk -U add openssl
#
#ENV VERSION v0.5.0
#ENV DOWNLOAD_URL https://github.com/jwilder/dockerize/releases/download/$VERSION/dockerize-alpine-linux-amd64-$VERSION.tar.gz
#
#RUN wget -qO- $DOWNLOAD_URL | tar xvz -C /usr/local/bin

