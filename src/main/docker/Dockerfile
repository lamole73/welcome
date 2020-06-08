FROM openjdk:8-jdk-alpine
ARG NAME
ADD $NAME app.jar
VOLUME /tmp
VOLUME /certificates
ENTRYPOINT exec java $JAVA_OPTIONS -jar /app.jar
