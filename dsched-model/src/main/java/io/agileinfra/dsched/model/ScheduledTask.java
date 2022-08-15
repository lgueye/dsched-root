package io.agileinfra.dsched.model;

import java.time.Instant;
import java.util.UUID;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = { "id", "triggerLocation", "label", "triggerAt", "status" })
public class ScheduledTask {

  private UUID id;
  private String triggerLocation;
  private String label;
  private Instant triggerAt;

  @Builder.Default
  private TaskStatus status = TaskStatus.SUBMITTED;

  private Instant created;
}
