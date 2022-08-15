package io.agileinfra.dsched.broker.consumer;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProcessingContext {

  private Instant timestamp;
  private String source;
  private String message;
}
