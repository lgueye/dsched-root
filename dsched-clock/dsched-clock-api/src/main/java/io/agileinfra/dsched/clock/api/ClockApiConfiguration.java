package io.agileinfra.dsched.clock.api;

import io.agileinfra.dsched.clock.model.ClockApi;
import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(RestConfiguration.class)
public class ClockApiConfiguration {

  @Profile("e2e")
  @Bean
  public ClockApi clockApiE2E(@Value("${clock.server.url}") final String apiUrl, final RestTemplate restTemplate) {
    return new ClockApiE2EImpl(apiUrl, restTemplate);
  }

  @Profile("default")
  @Bean
  public ClockApi clockApi() {
    return new ClockApiImpl();
  }
}
