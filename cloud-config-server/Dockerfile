FROM openjdk:8

COPY target/*.jar cloud-config-server.jar

ENTRYPOINT ["java", "-jar", "cloud-config-server.jar"]

EXPOSE 9296