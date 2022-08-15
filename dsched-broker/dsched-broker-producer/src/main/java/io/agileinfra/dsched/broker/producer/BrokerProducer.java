package io.agileinfra.dsched.broker.producer;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.SourceTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
@RequiredArgsConstructor
public class BrokerProducer {

  private final JmsTemplate jmsTemplate;

  public void scheduleTask(final ScheduledTask task) {
    jmsTemplate.convertAndSend(SourceTopics.SCHEDULED_TASKS, task);
    log.info("Successfully sent {} to {}", task, SourceTopics.SCHEDULED_TASKS);
  }
}
