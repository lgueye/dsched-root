package io.agileinfra.dsched.notification.model;

import java.util.List;

public interface NotificationApi {
  void subscribe();

  List<ScheduledTaskNotification> getNotifications();
}
