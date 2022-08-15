package io.agileinfra.dsched.broker.consumer;

import io.agileinfra.dsched.model.SourceTopics;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;

@RequiredArgsConstructor
@Slf4j
public class BrokerConsumer {

  private final BrokerReactor reactor;

  @JmsListener(destination = SourceTopics.SCHEDULED_TASKS, concurrency = "1", containerFactory = "jmsListenerContainerFactory")
  public void onScheduledTask(@Payload final String message) {
    log.info("Successfully received {} from {} at {}", message, SourceTopics.SCHEDULED_TASKS, Instant.now());
    reactor.process(message, SourceTopics.SCHEDULED_TASKS);
  }
}
