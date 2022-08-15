package io.agileinfra.dsched.model;

import java.util.UUID;

public interface ScheduledTaskProducer {
  void schedule(final UUID id, final ScheduledTask scheduledTask);
}
