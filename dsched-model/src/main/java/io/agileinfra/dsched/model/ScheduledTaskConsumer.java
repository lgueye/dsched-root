package io.agileinfra.dsched.model;

import java.util.List;

public interface ScheduledTaskConsumer {
  List<ScheduledTask> findAllSchedules();
}
