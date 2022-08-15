package io.agileinfra.dsched.broker.e2e.consumer.server;

import com.google.common.collect.Maps;
import io.agileinfra.dsched.model.ScheduledTask;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ScheduledTaskRepository {

  private final Map<UUID, ScheduledTask> store = Maps.newHashMap();

  public void persist(final ScheduledTask scheduledTask) {
    store.put(scheduledTask.getId(), scheduledTask);
  }

  public List<ScheduledTask> findAll() {
    return store.values().stream().sorted(Comparator.comparing(ScheduledTask::getCreated)).collect(Collectors.toList());
  }
}
