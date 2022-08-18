package io.agileinfra.dsched.scheduler.consumer.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import io.agileinfra.dsched.broker.consumer.BrokerConsumerConfiguration;
import io.agileinfra.dsched.broker.consumer.IncomingMessageProcessor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(BrokerConsumerConfiguration.class)
@Slf4j
public class SchedulerConsumerServerConfiguration {

  @Bean
  ScheduledTaskRepository scheduledTaskRepository(final HazelcastInstance hazelcastInstance) {
    return new ScheduledTaskRepository(hazelcastInstance);
  }

  @Bean
  ScheduledExecutorService scheduledExecutorService() {
    return Executors.newScheduledThreadPool(10);
  }

  @Bean
  SchedulerService schedulerService(
    final ScheduledExecutorService scheduledExecutorService,
    final ScheduledTaskRepository repository,
    final RestTemplate restTemplate
  ) {
    return new SchedulerService(scheduledExecutorService, repository, restTemplate);
  }

  @Bean
  IncomingMessageProcessor scheduledTaskProcessor(final ObjectMapper objectMapper, final SchedulerService schedulerService) {
    return new ScheduledTaskProcessor(objectMapper, schedulerService);
  }
}
