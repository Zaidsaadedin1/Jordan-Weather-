FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/JordanWeather-0.0.1-SNAPSHOT.jar JordanWeather.jar
EXPOSE 5050
ENTRYPOINT ["java", "-jar", "/JordanWeather.jar"]
