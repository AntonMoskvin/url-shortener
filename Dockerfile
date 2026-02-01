# Этап 1: Сборка
FROM gradle:8.12-jdk21 AS builder

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle .

RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

COPY src src
RUN ./gradlew build -x test --no-daemon

# Этап 2: Запуск
FROM eclipse-temurin:21-jre

# ВАЖНО: задать WORKDIR ДО копирования
WORKDIR /app

COPY --from=builder /app/build/libs/url-shortener-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]