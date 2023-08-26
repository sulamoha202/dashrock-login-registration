FROM maven:3.8.5-openjdk-17 AS build
#WORKDIR /opt
COPY . /opt/
RUN ./opt/mvnw clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /opt/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","demo.jar"]
