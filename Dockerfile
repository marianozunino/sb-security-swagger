FROM adoptopenjdk/openjdk11:alpine
WORKDIR application
RUN true
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "app.jar"]