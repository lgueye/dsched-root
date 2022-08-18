package io.agileinfra.dsched.scheduler.consumer.server;

import io.agileinfra.dsched.model.ScheduledTask;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

  private final ScheduledExecutorService executorService;
  private final ScheduledTaskRepository repository;
  private final RestTemplate restTemplate;

  public void scheduleTask(final ScheduledTask task) {
    var delay = Duration.between(Instant.now(), task.getTriggerAt()).toMillis();
    repository.persist(task);
    executorService.schedule(new TaskExecution(task, repository, restTemplate), delay, TimeUnit.MILLISECONDS);
  }
}
