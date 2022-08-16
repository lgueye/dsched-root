package io.agileinfra.dsched.scheduler.consumer.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.dsched.broker.consumer.IncomingMessageProcessor;
import io.agileinfra.dsched.broker.consumer.ProcessingContext;
import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.SourceTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ScheduledTaskProcessor implements IncomingMessageProcessor {

  private final ObjectMapper objectMapper;
  private final ScheduledTaskRepository repository;

  @Override
  public boolean accept(final ProcessingContext context) {
    return context != null && context.getMessage() != null && SourceTopics.SCHEDULED_TASKS.equals(context.getSource());
  }

  @Override
  public void process(final ProcessingContext context) {
    var message = context.getMessage();
    var scheduledTaskClass = ScheduledTask.class;
    try {
      final var scheduledTask = objectMapper.readValue(message, scheduledTaskClass);
      repository.persist(scheduledTask.toBuilder().created(context.getTimestamp()).build());
      log.info("Successfully processed {} from {} at {}", scheduledTask, SourceTopics.SCHEDULED_TASKS, scheduledTask.getCreated());
    } catch (JsonProcessingException e) {
      var classSimpleName = scheduledTaskClass.getSimpleName();
      log.error("Failed to parse incoming message {} into {}", message, classSimpleName);
      throw new IllegalArgumentException("Message " + message + " is not of type " + classSimpleName);
    }
  }
}
