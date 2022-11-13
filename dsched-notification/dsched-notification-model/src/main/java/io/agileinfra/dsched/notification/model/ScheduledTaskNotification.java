package io.agileinfra.dsched.notification.model;

import io.agileinfra.dsched.model.ScheduledTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ScheduledTaskNotification {

  private ScheduledTask task;
  private String node;
}
