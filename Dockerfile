# Этап 1: Сборка
FROM gradle:8.12-jdk21 AS builder

WORKDIR /app

# Копируем файлы для зависимостей (кэширование)
COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .

# Загружаем зависимости
RUN ./gradlew dependencies --no-daemon

# Копируем исходный код
COPY src src

# Собираем JAR
RUN ./gradlew build -x test --no-daemon

# Этап 2: Запуск
FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем JAR из этапа builder
COPY --from=builder /app/build/libs/url-shortener-*.jar app.jar

# Запускаем
ENTRYPOINT ["java", "-jar", "app.jar"]