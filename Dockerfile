FROM openjdk:17-jdk-alpine3.14
EXPOSE 8080
ARG JAR_FILE=build/libs/ap-du-lookup-ms-0.0.1.jar
ADD ${JAR_FILE} app.jar
RUN apk update && apk add bash
RUN mkdir -p /tmp/local/config
RUN touch /tmp/local/config/config.properties
ENTRYPOINT ["java","-jar","/app.jar"]
