FROM openjdk:8-jdk-alpine as fundraiserApp
COPY target/fundraiser.jar fundraiser.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/fundraiser.jar"]