FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/linkshortner-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
