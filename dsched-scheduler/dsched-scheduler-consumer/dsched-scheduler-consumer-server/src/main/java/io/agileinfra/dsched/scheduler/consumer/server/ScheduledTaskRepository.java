package io.agileinfra.dsched.scheduler.consumer.server;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.agileinfra.dsched.broker.producer.BrokerProducer;
import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.SourceTopics;
import io.agileinfra.dsched.notification.model.ScheduledTaskNotification;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
public class ScheduledTaskRepository {

  private final HazelcastInstance store;
  private final BrokerProducer brokerProducer;
  private final Environment environment;

  public void persist(final ScheduledTask scheduledTask) {
    store.getMap(SourceTopics.SCHEDULED_TASKS).put(scheduledTask.getId(), scheduledTask);
    var node = environment.getProperty("node");
    var notification = ScheduledTaskNotification.builder().task(scheduledTask).node(node).build();
    brokerProducer.publishScheduledTaskNotification(notification);
  }

  public boolean tryLock(final ScheduledTask scheduledTask) {
    return store.getMap(SourceTopics.SCHEDULED_TASKS).tryLock(scheduledTask.getId());
  }

  public void tryUnlock(final ScheduledTask scheduledTask) {
    final IMap<Object, Object> map = store.getMap(SourceTopics.SCHEDULED_TASKS);
    final UUID id = scheduledTask.getId();
    if (map.isLocked(id)) {
      map.unlock(id);
    }
  }

  public List<ScheduledTask> findAll() {
    return store
      .getMap(SourceTopics.SCHEDULED_TASKS)
      .values()
      .stream()
      .map(o -> (ScheduledTask) o)
      .sorted(Comparator.comparing(ScheduledTask::getCreated))
      .collect(Collectors.toList());
  }

  public ScheduledTask findById(UUID id) {
    return (ScheduledTask) store.getMap(SourceTopics.SCHEDULED_TASKS).get(id);
  }
}
