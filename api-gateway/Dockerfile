FROM openjdk:8
ADD target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]