package io.agileinfra.dsched.notification.e2e.tests.steps;

import io.agileinfra.dsched.broker.producer.BrokerProducer;
import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.notification.e2e.tests.E2ENotificationCucumberTestConfiguration;
import io.agileinfra.dsched.notification.model.NotificationApi;
import io.agileinfra.dsched.notification.model.ScheduledTaskNotification;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@CucumberContextConfiguration
@ContextConfiguration(classes = { E2ENotificationCucumberTestConfiguration.class })
@TestPropertySource(value = { "classpath:application-test.properties" })
@RequiredArgsConstructor
@Slf4j
public class E2ENotificationSteps {

  private final BrokerProducer producer;
  private final NotificationApi notificationApi;

  @DataTableType
  public ScheduledTaskNotification notificationEntryTransformer(Map<String, String> entry) {
    var scheduledTask = ScheduledTask
      .builder()
      .id(UUID.fromString(entry.get("id")))
      .triggerLocation(entry.get("triggerLocation"))
      .label(entry.get("label"))
      .triggerAt(Instant.parse(entry.get("triggerAt")))
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

  @When("producer posts scheduled tasks notifications:")
  public void producerPostsScheduledTasks(final List<ScheduledTaskNotification> notifications) {
    notifications.forEach(producer::publishScheduledTaskNotification);
  }

  @Then("ws consumers get notifications:")
  public void wsConsumersGetNotifications(final List<ScheduledTaskNotification> expected) {
    Awaitility
      .await()
      .pollInterval(Duration.ofMillis(500))
      .atMost(Duration.ofSeconds(2))
      .until(() -> {
        var notifications = notificationApi.getNotifications();
        log.info("ws client received notifications {}", notifications);
        return expected.equals(notifications);
      });
  }
}
