FROM openjdk:11.0.16-slim
RUN apt-get update && apt-get install curl -y
EXPOSE 8080
ADD target/dsched-scheduler-consumer-server*.jar dsched-scheduler-consumer-server.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-scheduler-consumer-server.jar"]
