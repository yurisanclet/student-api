FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY src /app/src
COPY .mvn /app/.mvn
COPY mvnw /app/mvnw
RUN mvn clean package -D skipTests

FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]