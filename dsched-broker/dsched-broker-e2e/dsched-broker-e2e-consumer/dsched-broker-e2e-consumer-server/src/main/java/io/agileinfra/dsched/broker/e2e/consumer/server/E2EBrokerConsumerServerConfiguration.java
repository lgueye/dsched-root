package io.agileinfra.dsched.broker.e2e.consumer.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.dsched.broker.consumer.BrokerConsumerConfiguration;
import io.agileinfra.dsched.broker.consumer.IncomingMessageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BrokerConsumerConfiguration.class)
public class E2EBrokerConsumerServerConfiguration {

  @Bean
  ScheduledTaskRepository scheduledTaskRepository() {
    return new ScheduledTaskRepository();
  }

  @Bean
  IncomingMessageProcessor scheduledTaskProcessor(final ObjectMapper objectMapper, final ScheduledTaskRepository repository) {
    return new ScheduledTaskProcessor(objectMapper, repository);
  }
}
