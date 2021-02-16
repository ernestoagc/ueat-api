FROM maven:3.6.3-jdk-11 AS builder
WORKDIR /app
RUN git clone https://github.com/ernestoagc/ueat-api.git
RUN chmod -R 777 ueat-api
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
#RUN mvn clean install -DskipTests=true
RUN mvn clean install

FROM openjdk:11.0.8-jre-slim
COPY --from=builder /app/target/soaint-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8500
CMD ["java", "-jar","/app.jar"]