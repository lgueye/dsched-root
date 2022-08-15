package io.agileinfra.dsched.broker.e2e.consumer.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class E2EBrokerConsumerServer {

  public static void main(String[] args) {
    SpringApplication.run(E2EBrokerConsumerServer.class, args);
  }
}
