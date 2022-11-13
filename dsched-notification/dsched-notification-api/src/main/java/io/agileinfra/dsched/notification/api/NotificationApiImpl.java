package io.agileinfra.dsched.notification.api;

import com.google.common.collect.Lists;
import io.agileinfra.dsched.notification.model.NotificationApi;
import io.agileinfra.dsched.notification.model.ScheduledTaskNotification;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@RequiredArgsConstructor
@Slf4j
public class NotificationApiImpl implements NotificationApi {

  private final String apiUrl;
  private final WebSocketStompClient stompClient;
  private final Duration connectTimeout; // Duration.parse("PT1S")
  private List<ScheduledTaskNotification> notifications;

  @Override
  public void subscribe() {
    notifications = Lists.newArrayList(); // reset notifications each time we subscribe
    // reset frame handler and session handler instances each time we subscribe
    var sessionHandler = new NotificationSessionHandler(new NotificationStompFrameHandler(notifications));
    stompClient.connect(apiUrl, sessionHandler);

    Awaitility
      .await() //
      .atMost(connectTimeout.toMillis(), TimeUnit.MILLISECONDS) //
      .pollDelay(50, TimeUnit.MILLISECONDS) //
      .pollInterval(50, TimeUnit.MILLISECONDS) //
      .until(sessionHandler::connected);
  }

  @Override
  public List<ScheduledTaskNotification> getNotifications() {
    return notifications;
  }
}
