FROM openjdk:8-alpine

LABEL "Maintainer"="Ghanshyam"

add target/*.jar user-order-service.jar

ENTRYPOINT ["java", "-jar", "user-order-service.jar"]