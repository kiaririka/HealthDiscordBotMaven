# Use the official Maven image as the base image
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the source code and pom.xml to the container
COPY . /app/

# Build the application using Maven
RUN mvn clean install -DskipTests

# Use a lightweight Java runtime as the base image for the final stage
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the final stage
COPY --from=build /app/target/MavenChatbot-1.0-SNAPSHOT.jar /app/