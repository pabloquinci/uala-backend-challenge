FROM eclipse-temurin:17

LABEL mentainer="javaguides.net@gmail.com"

WORKDIR /app

COPY target/uala-backend-challenge-0.0.1-SNAPSHOT.jar /app/uala-backend-challenge-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "uala-backend-challenge-0.0.1-SNAPSHOT.jar"]