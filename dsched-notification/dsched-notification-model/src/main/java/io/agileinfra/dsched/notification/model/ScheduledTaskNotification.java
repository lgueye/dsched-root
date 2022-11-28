package io.agileinfra.dsched.notification.model;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.TaskStatus;
import java.time.Instant;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = { "node" })
public class ScheduledTaskNotification {

  private ScheduledTask task;
  private String node;

  public Instant getTime() {
    if (task == null || task.getStatus() == null) {
      return null;
    }
    if (TaskStatus.SUBMITTED.equals(task.getStatus())) {
      return task.getCreated();
    }
    return task.getTriggerAt();
  }
}
