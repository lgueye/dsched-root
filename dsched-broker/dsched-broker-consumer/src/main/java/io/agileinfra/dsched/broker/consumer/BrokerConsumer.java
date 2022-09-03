package io.agileinfra.dsched.broker.consumer;

import io.agileinfra.dsched.clock.model.ClockApi;
import io.agileinfra.dsched.model.SourceTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;

@RequiredArgsConstructor
@Slf4j
public class BrokerConsumer {

  private final BrokerReactor reactor;
  private final ClockApi clockApi;

  @JmsListener(destination = SourceTopics.SCHEDULED_TASKS, concurrency = "1", containerFactory = "jmsListenerContainerFactory")
  public void onScheduledTask(@Payload final String message) {
    log.info("Successfully received {} from {} at {}", message, SourceTopics.SCHEDULED_TASKS, clockApi.now());
    reactor.process(message, SourceTopics.SCHEDULED_TASKS);
  }
}
