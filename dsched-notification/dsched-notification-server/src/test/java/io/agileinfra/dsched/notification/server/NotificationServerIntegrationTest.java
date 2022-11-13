package io.agileinfra.dsched.notification.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "spring.activemq.broker-url=vm://localhost" })
class NotificationServerIntegrationTest {

  @Test
  void contextLoads() {}
}
