package io.agileinfra.dsched.broker.e2e.producer.client;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.ScheduledTaskProducer;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
public class E2EBrokerProducerClient implements ScheduledTaskProducer {

  private final String apiUrl;
  private final RestTemplate restTemplate;

  @Override
  public void schedule(UUID id, ScheduledTask scheduledTask) {
    var uri = UriComponentsBuilder.fromHttpUrl(apiUrl).path("/api/v1/schedules/{id}").buildAndExpand(Map.of("id", id)).toUri();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(scheduledTask, headers), Void.class);
  }
}
