FROM openjdk:8

COPY target/*.jar user-service.jar

ENTRYPOINT ["java","-jar","/user-service.jar"]

EXPOSE 9001