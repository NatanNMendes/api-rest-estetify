# Etapa 1: Build com JDK 22 (gradle via ./gradlew do projeto)
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app
COPY . /app

# Dá permissão para o wrapper
RUN chmod +x ./gradlew

# Executa o build com o wrapper (usa Gradle 8.12.1 se for o que está no wrapper)
RUN ./gradlew clean build --no-daemon

# Etapa 2: Imagem final com JDK 22
FROM eclipse-temurin:22-jdk
WORKDIR /app
COPY --from=build /app/build/libs/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
