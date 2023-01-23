FROM openjdk:8

COPY target/*.jar cloud-gateway.jar

ENTRYPOINT ["java", "-jar", "cloud-gateway.jar"]

EXPOSE 9191