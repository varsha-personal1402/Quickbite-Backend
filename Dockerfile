# Use OpenJDK 17
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar file
COPY target/quickbite-backend.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
