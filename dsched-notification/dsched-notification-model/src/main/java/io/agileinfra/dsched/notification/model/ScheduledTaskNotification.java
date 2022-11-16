package io.agileinfra.dsched.notification.model;

import io.agileinfra.dsched.model.ScheduledTask;
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
}
