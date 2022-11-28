package io.agileinfra.dsched.notification.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.TaskStatus;
import java.time.Instant;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Slf4j
class ScheduledTaskNotificationTest {

  private ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).build();

  @Test
  void nullTime() {
    // Given
    var task = ScheduledTask
      .builder()
      .id(UUID.randomUUID())
      .label(RandomStringUtils.randomAlphanumeric(15))
      .triggerAt(Instant.parse("2023-04-01T10:05:00.000Z"))
      .build();
    var node = "any-scheduler-node";
    var underTest = ScheduledTaskNotification.builder().task(task).node(node).build();
    // When
    final Instant time = underTest.getTime();

    // Then
    assertThat(time).isNull();
  }

  @Test
  void timeFromCreatedTime() {
    // Given
    final Instant created = Instant.parse("2023-03-31T10:05:00.000Z");
    final Instant triggerAt = Instant.parse("2023-04-01T10:05:00.000Z");
    var task = ScheduledTask
      .builder()
      .id(UUID.randomUUID())
      .label(RandomStringUtils.randomAlphanumeric(15))
      .triggerAt(triggerAt)
      .created(created)
      .build();
    var node = "any-scheduler-node";
    var underTest = ScheduledTaskNotification.builder().task(task).node(node).build();
    // When
    final Instant time = underTest.getTime();

    // Then
    assertThat(time).isEqualTo(created);
  }

  @Test
  void timeFromTriggeredTime() {
    // Given
    final Instant created = Instant.parse("2023-03-31T10:05:00.000Z");
    final Instant triggerAt = Instant.parse("2023-04-01T10:05:00.000Z");
    var task = ScheduledTask
      .builder()
      .id(UUID.randomUUID())
      .label(RandomStringUtils.randomAlphanumeric(15))
      .triggerAt(triggerAt)
      .created(created)
      .status(TaskStatus.EXECUTED)
      .build();
    var node = "any-scheduler-node";
    var underTest = ScheduledTaskNotification.builder().task(task).node(node).build();
    // When
    final Instant time = underTest.getTime();

    // Then
    assertThat(time).isEqualTo(triggerAt);
  }

  @Test
  void shouldSerializeProperly() throws JsonProcessingException {
    // Given
    final Instant created = Instant.parse("2023-03-31T10:05:00.000Z");
    final Instant triggerAt = Instant.parse("2023-04-01T10:05:00.000Z");
    var task = ScheduledTask
      .builder()
      .id(UUID.fromString("0eb5f9c6-d8b2-4719-8e99-0ff48a3e81b4"))
      .label("task label")
      .triggerAt(triggerAt)
      .triggerLocation("https://www.google.com")
      .created(created)
      .build();
    var node = "any-scheduler-node";
    var underTest = ScheduledTaskNotification.builder().task(task).node(node).build();
    // When
    var actual = objectMapper.writeValueAsString(underTest);
    log.debug("{}", actual);

    // Then
    assertThat(actual)
      .isEqualTo(
        "{\"task\":{\"id\":\"0eb5f9c6-d8b2-4719-8e99-0ff48a3e81b4\",\"triggerLocation\":\"https://www.google.com\",\"label\":\"task label\",\"triggerAt\":\"2023-04-01T10:05:00Z\",\"status\":\"SUBMITTED\",\"created\":\"2023-03-31T10:05:00Z\"},\"node\":\"any-scheduler-node\",\"time\":\"2023-03-31T10:05:00Z\"}"
      );
  }
}
