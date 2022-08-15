package io.agileinfra.dsched.broker.e2e.consumer.client;

import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(RestConfiguration.class)
public class E2EBrokerConsumerClientConfiguration {

  @Bean
  E2EBrokerConsumerClient e2EBrokerConsumerClient01(
    @Value("${e2e.broker.consumer01.client.api.url}") String apiUrl,
    final RestTemplate restTemplate
  ) {
    return new E2EBrokerConsumerClient(apiUrl, restTemplate);
  }

  @Bean
  E2EBrokerConsumerClient e2EBrokerConsumerClient02(
    @Value("${e2e.broker.consumer02.client.api.url}") String apiUrl,
    final RestTemplate restTemplate
  ) {
    return new E2EBrokerConsumerClient(apiUrl, restTemplate);
  }
}
