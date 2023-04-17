
FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-ea-3-jdk-oraclelinux7
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG API_SECRET_KEY
ARG API_EXPIRE_LENGTH

ARG DB_IP 
ARG DB_PORT
ARG DB_NAME
ARG DB_PASSWORD

ENTRYPOINT java -jar -Dspring.profiles.active=dev app.jar