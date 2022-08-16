package io.agileinfra.dsched.scheduler.consumer.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "spring.activemq.broker-url=vm://localhost" })
class SchedulerConsumerServerIntegrationTest {

  @Test
  void contextLoads() {}
}
