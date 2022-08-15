FROM openjdk:11.0.16-slim
RUN apt-get update && apt-get install curl -y
EXPOSE 8080
EXPOSE 61616
ADD target/dsched-broker-server-*.jar dsched-broker-server.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-broker-server.jar"]
