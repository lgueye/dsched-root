package io.agileinfra.dsched.broker.consumer;

import io.agileinfra.dsched.model.SourceQueues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;

@RequiredArgsConstructor
@Slf4j
public class ScheduledTasksNotificationsBrokerConsumer {

  private final BrokerReactor reactor;

  @JmsListener(destination = SourceQueues.SCHEDULED_TASKS_NOTIFICATIONS, concurrency = "1", containerFactory = "jmsQueueListenerContainerFactory")
  public void onScheduledTaskNotification(@Payload final String message) {
    reactor.process(message, SourceQueues.SCHEDULED_TASKS_NOTIFICATIONS);
  }
}
