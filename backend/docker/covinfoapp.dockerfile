FROM amazoncorretto:11-alpine-jdk
COPY . /backend
WORKDIR /backend
RUN ./mvnw install
ENTRYPOINT ["java", "-jar", "app/target/app-0.0.1.jar"]
EXPOSE 8080
