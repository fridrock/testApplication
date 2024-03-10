FROM openjdk:21-slim
EXPOSE 8080
ARG JAR_FILE=target/testApplication-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]