FROM openjdk:17-jdk-slim
LABEL maintainer=fetrova.com

COPY target/accounts-microservices-0.0.1-SNAPSHOT.jar accounts.jar
ENTRYPOINT ["java","-jar","accounts.jar"]