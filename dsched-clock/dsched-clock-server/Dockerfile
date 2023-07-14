FROM eclipse-temurin:17
RUN apt-get update && apt-get install curl -y
EXPOSE 8080
ADD target/dsched-clock-server*.jar dsched-clock-server.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-clock-server.jar"]
