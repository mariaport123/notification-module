# Stage 1: Build the application using Gradle and Java 25
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the source code
COPY src src

# Grant execution permissions to the Gradle wrapper
RUN chmod +x gradlew

# Build the application and generate the executable JAR
# The -x test flag is optional if you want to skip tests during image build
RUN ./gradlew bootJar -x test

# Stage 2: Create the final production image with Java 25
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app

# Copy the built JAR from the build stage to the production stage
# Spring Boot with Gradle typically outputs the JAR in build/libs/
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]