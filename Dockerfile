FROM eclipse-temurin:21 AS builder

COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

RUN chmod +x ./gradlew && ./gradlew dependencies

COPY src src

RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre AS runtime

COPY --from=builder /build/libs/*.jar /app.jar

ENV TZ=Asia/Seoul
ARG PROFILE=dev
ENV PROFILE=${PROFILE}

EXPOSE 8080
EXPOSE 1010

CMD ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app.jar"]
