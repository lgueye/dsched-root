package io.agileinfra.dsched.scheduler.consumer.client;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.ScheduledTaskConsumer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
public class SchedulerConsumerClient implements ScheduledTaskConsumer {

  private final String apiUrl;
  private final RestTemplate restTemplate;

  @Override
  public List<ScheduledTask> findAllSchedules() {
    var uri = UriComponentsBuilder.fromHttpUrl(apiUrl).path("/api/v1/schedules").build().toUri();
    var headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    final ParameterizedTypeReference<List<ScheduledTask>> responseType = new ParameterizedTypeReference<>() {};
    final List<ScheduledTask> tasks = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), responseType).getBody();
    if (tasks == null) {
      return Collections.emptyList();
    }
    return tasks.stream().sorted(Comparator.comparing(ScheduledTask::getTriggerAt)).collect(Collectors.toList());
  }
}
