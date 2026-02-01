FROM eclipse-temurin:21-jre
COPY build/libs/url-shortener-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]