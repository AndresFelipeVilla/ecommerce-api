FROM eclipse-temurin:17-jdk-alpine AS base

WORKDIR /app

COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw mvnw.cmd ./

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B --no-transfer-progress

COPY src/ src/


FROM base AS development

ENV SPRING_PROFILES_ACTIVE=dev
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 \
    -Dspring.devtools.restart.enabled=true \
    -Dfile.encoding=UTF-8"

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT ["./mvnw", "spring-boot:run", \
    "-Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", \
    "--no-transfer-progress"]


FROM base AS builder

RUN ./mvnw package -DskipTests -B --no-transfer-progress

RUN java -Djarmode=layertools \
    -jar target/spring-boot-template-0.0.1-SNAPSHOT.jar \
    extract --destination target/extracted


FROM eclipse-temurin:17-jre-alpine AS production

LABEL maintainer="Felipe Villa"
LABEL org.opencontainers.image.title="Spring Boot Template"
LABEL org.opencontainers.image.description="Spring Boot 4 + Java 17 + PostgreSQL"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

COPY --from=builder --chown=appuser:appgroup /app/target/extracted/dependencies/ ./
COPY --from=builder --chown=appuser:appgroup /app/target/extracted/spring-boot-loader/ ./
COPY --from=builder --chown=appuser:appgroup /app/target/extracted/snapshot-dependencies/ ./
COPY --from=builder --chown=appuser:appgroup /app/target/extracted/application/ ./

USER appuser

EXPOSE 8080

ENV JAVA_OPTS="-XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=75.0 \
    -XX:+UseG1GC \
    -Djava.security.egd=file:/dev/./urandom \
    -Dfile.encoding=UTF-8"

ENV SPRING_PROFILES_ACTIVE=prod

HEALTHCHECK --interval=30s --timeout=10s --retries=3 --start-period=45s \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]
