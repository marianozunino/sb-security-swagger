FROM adoptopenjdk/openjdk11:alpine as builder
WORKDIR /workspace/app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk/openjdk11:alpine
WORKDIR application
COPY --from=builder workspace/app/dependencies/ ./
COPY --from=builder workspace/app/spring-boot-loader/ ./
COPY --from=builder workspace/app/snapshot-dependencies/ ./
COPY --from=builder workspace/app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]