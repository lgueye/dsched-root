package io.agileinfra.dsched.scheduler.consumer.client;

import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(RestConfiguration.class)
public class SchedulerConsumerClientConfiguration {

  @Bean
  SchedulerConsumerClient schedulerConsumerClient01(@Value("${scheduler.consumer01.client.api.url}") String apiUrl, final RestTemplate restTemplate) {
    return new SchedulerConsumerClient(apiUrl, restTemplate);
  }

  @Bean
  SchedulerConsumerClient schedulerConsumerClient02(@Value("${scheduler.consumer02.client.api.url}") String apiUrl, final RestTemplate restTemplate) {
    return new SchedulerConsumerClient(apiUrl, restTemplate);
  }
}
