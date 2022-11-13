package io.agileinfra.dsched.notification.api;

import io.agileinfra.dsched.notification.model.ScheduledTaskNotification;
import java.lang.reflect.Type;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

@Slf4j
@RequiredArgsConstructor
public class NotificationStompFrameHandler implements StompFrameHandler {

  private final List<ScheduledTaskNotification> notifications;

  @Override
  public Type getPayloadType(StompHeaders headers) {
    return ScheduledTaskNotification.class;
  }

  @Override
  public void handleFrame(StompHeaders headers, Object payload) {
    notifications.add((ScheduledTaskNotification) payload);
  }
}
