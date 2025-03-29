FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /weatherDelivery

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

# Construir aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final con OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine
#FROM openjdk:17-jdk-slim
WORKDIR /weatherDelivery

#COPY --from=build /weatherDelivery/target/weather-delivery.jar weather-delivery.jar
COPY --from=build weatherDelivery/target/*.jar weather-delivery.jar

EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "weather-delivery.jar"]