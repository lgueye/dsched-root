package io.agileinfra.dsched.rest.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

  @Bean
  ObjectMapper objectMapper() {
    return Jackson2ObjectMapperBuilder
      .json() //
      .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
      .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // use ISODate and not other cryptic formats
      .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS) // allow empty json to be produced
      .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // do not fail on unknown properties
      .build();
  }

  @Bean
  RestTemplate restTemplate(final ObjectMapper objectMapper) {
    var restTemplate = new RestTemplate();
    var httpMessageConverters = restTemplate
      .getMessageConverters()
      .stream()
      .filter(mapper -> !(mapper instanceof MappingJackson2HttpMessageConverter))
      .collect(Collectors.toList());
    var jsonConverter = new MappingJackson2HttpMessageConverter(objectMapper);
    httpMessageConverters.add(jsonConverter);
    restTemplate.setMessageConverters(httpMessageConverters);
    return restTemplate;
  }
}
