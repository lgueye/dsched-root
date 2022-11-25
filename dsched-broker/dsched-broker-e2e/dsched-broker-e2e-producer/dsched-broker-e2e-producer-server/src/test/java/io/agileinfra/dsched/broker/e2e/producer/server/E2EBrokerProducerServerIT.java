package io.agileinfra.dsched.broker.e2e.producer.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "spring.activemq.broker-url=vm://localhost" })
class E2EBrokerProducerServerIT {

  @Test
  void contextLoads() {}
}
