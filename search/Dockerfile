FROM java:8-jre
VOLUME /tmp
ADD  target/search-1.0-SNAPSHOT.jar search.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/search.jar"]

#RUN apk -U add openssl
#
#ENV VERSION v0.5.0
#ENV DOWNLOAD_URL https://github.com/jwilder/dockerize/releases/download/$VERSION/dockerize-alpine-linux-amd64-$VERSION.tar.gz
#
#RUN wget -qO- $DOWNLOAD_URL | tar xvz -C /usr/local/bin

