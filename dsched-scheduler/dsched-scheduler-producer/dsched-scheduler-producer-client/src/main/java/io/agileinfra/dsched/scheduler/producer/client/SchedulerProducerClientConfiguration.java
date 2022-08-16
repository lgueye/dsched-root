package io.agileinfra.dsched.scheduler.producer.client;

import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(RestConfiguration.class)
public class SchedulerProducerClientConfiguration {

  @Bean
  SchedulerProducerClient schedulerProducerClient(@Value("${scheduler.producer.client.api.url}") String apiUrl, final RestTemplate restTemplate) {
    return new SchedulerProducerClient(apiUrl, restTemplate);
  }
}
