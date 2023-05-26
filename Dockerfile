FROM openjdk:19

WORKDIR /app
COPY src/main/java/isha.project /app
RUN mvn clean install
CMD ["java", "-cp", "/app", "DiscordBot"]
