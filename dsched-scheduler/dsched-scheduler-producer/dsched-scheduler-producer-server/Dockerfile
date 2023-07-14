FROM eclipse-temurin:17
RUN apt-get update && apt-get install curl -y
EXPOSE 8080
ADD target/dsched-scheduler-producer-server*.jar dsched-scheduler-producer-server.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-scheduler-producer-server.jar"]
