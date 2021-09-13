FROM amazoncorretto:11-alpine-jdk
COPY . /app
WORKDIR /app
RUN ./mvnw clean package
ENTRYPOINT ["java", "-jar", "/app/target/backend-0.0.1.jar"]
EXPOSE 8080
