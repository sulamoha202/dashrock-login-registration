FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /opt
COPY . .
COPY .env ./.env
RUN cat .env
RUN ls -la
RUN chmod +x mvnw
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /opt/target/registration-login-0.0.1-SNAPSHOT.jar dashrock.jar
COPY .env ./.env
EXPOSE 80
ENTRYPOINT ["java","-jar","dashrock.jar"]
