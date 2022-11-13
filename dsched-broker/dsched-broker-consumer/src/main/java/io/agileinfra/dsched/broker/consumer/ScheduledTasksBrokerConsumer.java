package io.agileinfra.dsched.broker.consumer;

import io.agileinfra.dsched.model.SourceTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;

@RequiredArgsConstructor
@Slf4j
public class ScheduledTasksBrokerConsumer {

  private final BrokerReactor reactor;

  @JmsListener(destination = SourceTopics.SCHEDULED_TASKS, concurrency = "1", containerFactory = "jmsTopicListenerContainerFactory")
  public void onScheduledTask(@Payload final String message) {
    reactor.process(message, SourceTopics.SCHEDULED_TASKS);
  }
}
