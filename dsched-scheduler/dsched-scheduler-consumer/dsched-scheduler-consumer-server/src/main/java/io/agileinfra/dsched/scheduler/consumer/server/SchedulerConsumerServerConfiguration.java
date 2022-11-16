package io.agileinfra.dsched.scheduler.consumer.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import io.agileinfra.dsched.broker.consumer.IncomingMessageProcessor;
import io.agileinfra.dsched.broker.consumer.ScheduledTasksBrokerConsumerConfiguration;
import io.agileinfra.dsched.broker.producer.BrokerProducer;
import io.agileinfra.dsched.broker.producer.BrokerProducerConfiguration;
import io.agileinfra.dsched.clock.api.ClockApiConfiguration;
import io.agileinfra.dsched.clock.model.ClockApi;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({ ScheduledTasksBrokerConsumerConfiguration.class, ClockApiConfiguration.class, BrokerProducerConfiguration.class })
@Slf4j
public class SchedulerConsumerServerConfiguration {

  @Bean
  ScheduledTaskRepository scheduledTaskRepository(
    final HazelcastInstance hazelcastInstance,
    final BrokerProducer brokerProducer,
    final Environment environment
  ) {
    return new ScheduledTaskRepository(hazelcastInstance, brokerProducer, environment);
  }

  @Bean
  ScheduledExecutorService scheduledExecutorService() {
    return Executors.newScheduledThreadPool(10);
  }

  @Bean
  SchedulerService schedulerService(
    final ScheduledExecutorService scheduledExecutorService,
    final ScheduledTaskRepository repository,
    final RestTemplate restTemplate,
    final ClockApi clockApi
  ) {
    return new SchedulerService(scheduledExecutorService, repository, restTemplate, clockApi);
  }

  @Bean
  IncomingMessageProcessor scheduledTaskProcessor(final ObjectMapper objectMapper, final SchedulerService schedulerService) {
    return new ScheduledTaskProcessor(objectMapper, schedulerService);
  }
}
