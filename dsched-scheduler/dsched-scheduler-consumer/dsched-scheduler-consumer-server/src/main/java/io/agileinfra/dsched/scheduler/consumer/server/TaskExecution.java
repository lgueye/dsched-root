package io.agileinfra.dsched.scheduler.consumer.server;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
public class TaskExecution implements Runnable {

  private final ScheduledTask task;
  private final ScheduledTaskRepository repository;
  private final RestTemplate restTemplate;

  @Override
  public void run() {
    var taskId = task.getId();
    var persisted = repository.findById(taskId);
    var alreadyExecuted = TaskStatus.EXECUTED.equals(persisted.getStatus());
    if (alreadyExecuted) {
      log.info("Ignored job {}: the job was already executed", taskId);
      return;
    }
    if (!repository.tryLock(persisted)) {
      log.info("Ignored job {}: job is already being executed (locked)", taskId);
      return;
    }
    // do the actual job
    var httpEntity = new HttpEntity<Void>(new HttpHeaders());
    var uri = UriComponentsBuilder.fromHttpUrl(persisted.getTriggerLocation()).build().toUri();
    var httpMethod = HttpMethod.POST;
    restTemplate.exchange(uri, httpMethod, httpEntity, Void.class);
    log.info("Executed job {}: at location {}", taskId, persisted.getTriggerLocation());

    repository.persist(persisted.toBuilder().status(TaskStatus.EXECUTED).build());
    repository.tryUnlock(task);
    log.info("Updated status and persisted job {}", taskId);
  }
}
