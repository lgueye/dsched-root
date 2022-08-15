package io.agileinfra.sched.broker.e2e.tests;

import io.cucumber.core.cli.Main;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class E2EBrokerTestRunner implements CommandLineRunner {

  public static void main(String[] args) {
    log.info("STARTING THE APPLICATION");
    SpringApplication.run(E2EBrokerTestRunner.class, args);
    log.info("APPLICATION FINISHED");
  }

  @Override
  public void run(String... args) {
    IntStream.range(0, args.length).forEach(index -> log.info("Value at Index : " + (index + 1) + " is " + args[index]));

    Main.run();
    log.info("Executed cucumber tests");
  }
}
