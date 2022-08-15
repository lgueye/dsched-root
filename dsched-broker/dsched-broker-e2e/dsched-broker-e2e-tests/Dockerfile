FROM openjdk:11.0.16-slim
RUN apt-get update && apt-get install curl -y
ADD target/dsched-broker-e2e-tests-*.jar dsched-broker-e2e-tests.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "dsched-broker-e2e-tests.jar"]
