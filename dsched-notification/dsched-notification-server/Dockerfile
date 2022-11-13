FROM openjdk:11.0.16-slim
RUN apt-get update && apt-get install curl -y
EXPOSE 8080
ADD target/dsched-notification-server*.jar dsched-notification-server.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-notification-server.jar"]
