package io.agileinfra.dsched.clock.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClockServer {

  public static void main(String[] args) {
    SpringApplication.run(ClockServer.class, args);
  }
}
