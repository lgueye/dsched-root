package io.agileinfra.dsched.scheduler.consumer.server;

import io.agileinfra.dsched.clock.model.ClockApi;
import io.agileinfra.dsched.model.ScheduledTask;
import java.time.Duration;
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
  private final ClockApi clockApi;

  public void scheduleTask(final ScheduledTask task) {
    repository.persist(task);
    var delay = Duration.between(clockApi.now(), task.getTriggerAt()).toMillis();
    executorService.schedule(new TaskExecution(task, repository, restTemplate), delay, TimeUnit.MILLISECONDS);
  }
}
