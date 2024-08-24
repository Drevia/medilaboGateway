FROM openjdk:17-jdk-alpine
LABEL org.opencontainers.image.authors="Cyriac"
COPY target/medilaboGateway-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]