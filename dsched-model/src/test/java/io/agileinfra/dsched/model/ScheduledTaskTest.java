package io.agileinfra.dsched.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScheduledTaskTest {

  @Test
  void taskStatusShouldDefaultToSubmitted() {
    // When
    var actual = ScheduledTask.builder().build();

    // Then
    assertThat(actual).isNotNull();
    assertThat(actual.getStatus()).isEqualTo(TaskStatus.SUBMITTED);
  }
}
