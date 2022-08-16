package io.agileinfra.dsched.scheduler.producer.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulerProducerServer {

  public static void main(String[] args) {
    SpringApplication.run(SchedulerProducerServer.class, args);
  }
}
