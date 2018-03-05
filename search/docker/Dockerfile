FROM java:8-jre
VOLUME /tmp
ADD  search-1.0-SNAPSHOT.jar search-1.0.jar
EXPOSE 8090
RUN sh -c 'touch /search-1.0.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/search-1.0.jar"]

#RUN apk -U add openssl
#
#ENV VERSION v0.5.0
#ENV DOWNLOAD_URL https://github.com/jwilder/dockerize/releases/download/$VERSION/dockerize-alpine-linux-amd64-$VERSION.tar.gz
#
#RUN wget -qO- $DOWNLOAD_URL | tar xvz -C /usr/local/bin

