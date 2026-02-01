FROM gradle:8.12-jdk21

WORKDIR /app

# Копируем всё
COPY . .

# Даём права
RUN chmod +x ./gradlew

# Собираем
RUN ./gradlew build -x test --no-daemon

# Запускаем
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "build/libs/url-shortener-0.0.1-SNAPSHOT.jar"]