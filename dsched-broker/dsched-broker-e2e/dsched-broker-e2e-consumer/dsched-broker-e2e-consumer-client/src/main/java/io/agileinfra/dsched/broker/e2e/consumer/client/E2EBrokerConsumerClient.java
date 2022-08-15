package io.agileinfra.dsched.broker.e2e.consumer.client;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.ScheduledTaskConsumer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
public class E2EBrokerConsumerClient implements ScheduledTaskConsumer {

  private final String apiUrl;
  private final RestTemplate restTemplate;

  @Override
  public List<ScheduledTask> findAllSchedules() {
    var uri = UriComponentsBuilder.fromHttpUrl(apiUrl).path("/api/v1/schedules").build().toUri();
    var headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    final ParameterizedTypeReference<List<ScheduledTask>> responseType = new ParameterizedTypeReference<>() {};
    return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), responseType).getBody();
  }
}
