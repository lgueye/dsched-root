package io.agileinfra.dsched.broker.producer;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.SourceQueues;
import io.agileinfra.dsched.model.SourceTopics;
import io.agileinfra.dsched.notification.model.ScheduledTaskNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
@RequiredArgsConstructor
public class BrokerProducer {

  private final JmsTemplate jmsTopicTemplate;
  private final JmsTemplate jmsQueueTemplate;

  public void scheduleTask(final ScheduledTask task) {
    jmsTopicTemplate.convertAndSend(SourceTopics.SCHEDULED_TASKS, task);
    log.info("Successfully sent {} to {}", task, SourceTopics.SCHEDULED_TASKS);
  }

  public void publishScheduledTaskNotification(final ScheduledTaskNotification notification) {
    jmsQueueTemplate.convertAndSend(SourceQueues.SCHEDULED_TASKS_NOTIFICATIONS, notification);
    log.info("Successfully sent {} to {}", notification, SourceQueues.SCHEDULED_TASKS_NOTIFICATIONS);
  }
}
