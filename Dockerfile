FROM eclipse-temurin:17-jdk-jammy

COPY target/TODO-1.0-SNAPSHOT-jar-with-dependencies.jar /app/todo.jar

ENTRYPOINT ["java", "-jar", "/app/todo.jar"]
