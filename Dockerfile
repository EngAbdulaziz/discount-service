FROM openjdk:11
LABEL maintainer="eng.abdalazizabdalbaset@gmail.com"
VOLUME /main-app
ADD target/discount-service-0.0.1-SNAPSHOT.jar discount-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/discount-service.jar"]