FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /workspace
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar --no-daemon

EXPOSE 8080

FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /workspace/$JAR_FILE app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
