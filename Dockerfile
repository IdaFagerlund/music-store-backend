# Multistage build to both create the jar and run the jar

# --- create jar ---
FROM openjdk:8-jdk-alpine AS build
RUN apk add --no-cache maven
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# --- run jar ---
FROM openjdk:8-jre-alpine
COPY --from=build /app/target/*.jar /app/target/webshop.jar
ENTRYPOINT ["java", "-jar", "/app/target/webshop.jar"]