# Use OpenJDK 17 as the base image
FROM eclipse-temurin:17.0.3_7-jdk-jammy

# Metadata as a label
LABEL maintainer="kemori@redhat.com" version="1.0" description="Camel Example"

# Copy the application JAR into the container
COPY target/camel-example-yaml-dsl-1.0-SNAPSHOT.jar /app/camel-example-service.jar

# Command to run the application
CMD ["java", "-jar", "/app/camel-example-service.jar"]