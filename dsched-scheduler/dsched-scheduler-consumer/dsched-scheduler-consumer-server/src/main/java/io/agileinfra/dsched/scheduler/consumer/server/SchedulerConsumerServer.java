package io.agileinfra.dsched.scheduler.consumer.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulerConsumerServer {

  public static void main(String[] args) {
    SpringApplication.run(SchedulerConsumerServer.class, args);
  }
}
