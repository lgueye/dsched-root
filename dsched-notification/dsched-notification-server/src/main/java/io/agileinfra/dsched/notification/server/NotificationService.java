package io.agileinfra.dsched.notification.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.dsched.broker.consumer.IncomingMessageProcessor;
import io.agileinfra.dsched.broker.consumer.ProcessingContext;
import io.agileinfra.dsched.model.SourceQueues;
import io.agileinfra.dsched.notification.model.ScheduledTaskNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Slf4j
@RequiredArgsConstructor
public class NotificationService implements IncomingMessageProcessor {

  private final ObjectMapper objectMapper;
  private final SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public boolean accept(ProcessingContext context) {
    return context != null && context.getMessage() != null && SourceQueues.SCHEDULED_TASKS_NOTIFICATIONS.equals(context.getSource());
  }

  @Override
  public void process(ProcessingContext context) {
    var message = context.getMessage();
    var clazz = ScheduledTaskNotification.class;
    ScheduledTaskNotification notification;
    try {
      notification = objectMapper.readValue(message, clazz);
      log.info("Successfully processed {} from {} at {}", notification, SourceQueues.SCHEDULED_TASKS_NOTIFICATIONS, context.getTimestamp());
    } catch (JsonProcessingException e) {
      var classSimpleName = clazz.getSimpleName();
      log.error("Failed to parse incoming message {} into {}", message, classSimpleName);
      throw new IllegalArgumentException("Message " + message + " is not of type " + classSimpleName);
    }
    try {
      simpMessagingTemplate.convertAndSend("/topic/scheduled-notifications", notification);
      log.info("Successfully broadcasted {} to ws consumers", notification);
    } catch (Exception e) {
      log.error("Failed to notify subscribers of {}", notification);
    }
  }
}
