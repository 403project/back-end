# FROM eclipse-temurin:21-jdk-alpine
FROM openjdk:21-slim as build

# Set a working directory
WORKDIR /app

# Copy the whole project to the working directory
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build

# CMD ["./gradlew", "clean", "build"]
# Assuming build is successful, copy the JAR file to the root
ARG JAR_FILE=./build/libs/*.jar
RUN cp ${JAR_FILE} /app.jar

# Expose the application's port
FROM openjdk:21-slim
COPY --from=build /app.jar /app.jar 
COPY --from=build . .
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=real"]

EXPOSE 8080

# Run the application
# ENTRYPOINT ["java","-jar","/app.jar"]
