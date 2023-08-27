FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /opt
COPY . .
RUN ls -la .
RUN chmod +x mvnw
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /opt
COPY --from=build target/registration-login-0.0.1-SNAPSHOT.jar dashrock.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","dashrock.jar"]
