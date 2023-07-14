FROM eclipse-temurin:17
RUN apt-get update && apt-get install curl -y
EXPOSE 8080
EXPOSE 61616
ADD target/dsched-broker-server-*.jar dsched-broker-server.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-broker-server.jar"]
