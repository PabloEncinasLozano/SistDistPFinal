#FROM openjdk:21-jdk-slim
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

#COPY target/frontend_folder-0.0.1-SNAPSHOT.jar app.jar
COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/frontend_folder-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
