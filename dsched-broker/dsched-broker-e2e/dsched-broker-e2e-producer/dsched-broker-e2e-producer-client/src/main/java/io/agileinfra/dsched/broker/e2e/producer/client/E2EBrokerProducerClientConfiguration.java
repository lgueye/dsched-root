package io.agileinfra.dsched.broker.e2e.producer.client;

import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(RestConfiguration.class)
public class E2EBrokerProducerClientConfiguration {

  @Bean
  E2EBrokerProducerClient e2EBrokerProducerClient(@Value("${e2e.broker.producer.client.api.url}") String apiUrl, final RestTemplate restTemplate) {
    return new E2EBrokerProducerClient(apiUrl, restTemplate);
  }
}
