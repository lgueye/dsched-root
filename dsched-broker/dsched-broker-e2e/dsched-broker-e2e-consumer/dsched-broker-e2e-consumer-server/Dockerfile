FROM eclipse-temurin:17
RUN apt-get update && apt-get install curl -y
EXPOSE 8080
ADD target/dsched-broker-e2e-consumer-server*.jar dsched-broker-e2e-consumer-server.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-broker-e2e-consumer-server.jar"]
