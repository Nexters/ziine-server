FROM eclipse-temurin:21 AS builder

COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

RUN chmod +x ./gradlew && ./gradlew dependencies

COPY src src

RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre AS runtime

COPY --from=builder /build/libs/*.jar /app.jar

ENV TZ=Asia/Seoul

ENV PROFILE=${PROFILE}

ENV DB_HOST=${DB_HOST}
ENV DB_PORT=${DB_PORT}
ENV DB_NAME=${DB_NAME}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

ENV MAIL_PASSWORD=${MAIL_PASSWORD}

ENV JWT_KEY=${JWT_KEY}

ENV AWS_S3_BUCKET=${AWS_S3_BUCKET}
ENV AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
ENV AWS_SECRET_KEY=${AWS_SECRET_KEY}

EXPOSE 8080
EXPOSE 1010

CMD ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app.jar"]
