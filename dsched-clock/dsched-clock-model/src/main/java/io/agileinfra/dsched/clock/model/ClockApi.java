package io.agileinfra.dsched.clock.model;

import java.time.Instant;

public interface ClockApi {
  void freeze(ClockDto request);

  void unfreeze();

  Instant now();
}
