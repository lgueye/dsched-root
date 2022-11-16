package io.agileinfra.dsched.scheduler.e2e.tests.steps;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.TaskStatus;
import io.agileinfra.dsched.notification.model.NotificationApi;
import io.agileinfra.dsched.notification.model.ScheduledTaskNotification;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;

@RequiredArgsConstructor
@Slf4j
public class E2ENotificationSteps {

  private final NotificationApi notificationApi;

  @DataTableType
  public ScheduledTaskNotification notificationEntryTransformer(Map<String, String> entry) {
    var scheduledTask = ScheduledTask
      .builder()
      .id(UUID.fromString(entry.get("id")))
      .triggerLocation(entry.get("triggerLocation"))
      .label(entry.get("label"))
      .triggerAt(Instant.parse(entry.get("triggerAt")))
      .status(TaskStatus.valueOf(entry.get("status")))
      .build();
    var node = entry.get("node");
    return ScheduledTaskNotification.builder().task(scheduledTask).node(node).build();
  }

  @Given("ws consumers subscribe to notifications")
  public void wsConsumersSubscribeToNotifications() {
    try {
      notificationApi.subscribe();
    } catch (Exception e) {
      log.error("Failed to subscribe to notifications API", e);
      throw e;
    }
  }

  @Then("ws consumers get notifications:")
  public void wsConsumersGetNotifications(final List<ScheduledTaskNotification> expected) {
    Awaitility
      .await()
      .pollInterval(Duration.ofMillis(500))
      .atMost(Duration.ofSeconds(5))
      .until(() -> {
        var notifications = notificationApi.getNotifications();
        log.info("ws client received notifications {}", notifications);
        log.info("EXPECTED {}", expected);
        log.info("ACTUAL {}", notifications);
        return (
          expected.equals(notifications) &&
          notifications.stream().allMatch(notification -> notification.getNode().contains("dsched-scheduler-consumer-server-"))
        );
      });
  }
}
