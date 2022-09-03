package io.agileinfra.dsched.scheduler.e2e.tests.steps;

import io.agileinfra.dsched.clock.model.ClockApi;
import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.scheduler.consumer.client.SchedulerConsumerClient;
import io.agileinfra.dsched.scheduler.e2e.tests.E2ESchedulerCucumberTestConfiguration;
import io.agileinfra.dsched.scheduler.producer.client.SchedulerProducerClient;
import io.cucumber.java.DataTableType;
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
@ContextConfiguration(classes = { E2ESchedulerCucumberTestConfiguration.class })
@TestPropertySource(value = { "classpath:application-test.properties" })
@RequiredArgsConstructor
@Slf4j
public class E2ESchedulerSteps {

  private final SchedulerProducerClient producer;
  private final List<SchedulerConsumerClient> consumers;
  private final ClockApi clockApi;

  @DataTableType
  public ScheduledTask taskEntryTransformer(Map<String, String> entry) {
    return ScheduledTask
      .builder()
      .id(UUID.fromString(entry.get("id")))
      .triggerLocation(entry.get("triggerLocation"))
      .label(entry.get("label"))
      .build();
  }

  @When("producer schedules tasks, {string} from now:")
  public void producer_schedules_tasks_from_now(String string, final List<ScheduledTask> tasks) {
    final Instant now = clockApi.now();
    tasks.forEach(task -> {
      final ScheduledTask scheduled = task.toBuilder().triggerAt(now.plus(Duration.parse(string))).build();
      producer.schedule(task.getId(), scheduled);
    });
  }

  @Then("within {string}, consumers get tasks:")
  public void within_consumers_get_tasks(String string, final List<ScheduledTask> expected) {
    Awaitility
      .await()
      .pollInterval(Duration.ofMillis(500))
      .atMost(Duration.parse(string))
      .until(() ->
        consumers
          .stream()
          .allMatch(consumer -> {
            var actual = consumer.findAllSchedules();
            log.info("Consumer {} received {}", consumer, actual);
            return actual.equals(expected);
          })
      );
  }
}
