# Этап 1: Сборка
FROM gradle:8.12-jdk21 AS builder

WORKDIR /app

# Копируем файлы
COPY gradle gradle
COPY gradlew .
COPY build.gradle .

# ВАЖНО: даём права на выполнение gradlew
RUN chmod +x ./gradlew

# Загружаем зависимости
RUN ./gradlew dependencies --no-daemon

# Копируем исходный код
COPY src src

# Собираем JAR
RUN ./gradlew build -x test --no-daemon

# Этап 2: Запуск
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/url-shortener-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]