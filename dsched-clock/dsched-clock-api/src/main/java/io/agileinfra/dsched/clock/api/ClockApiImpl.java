package io.agileinfra.dsched.clock.api;

import io.agileinfra.dsched.clock.model.ClockApi;
import io.agileinfra.dsched.clock.model.ClockDto;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ClockApiImpl implements ClockApi {

  @Override
  public void freeze(ClockDto newClock) {
    throw new UnsupportedOperationException(
      "Freezing clock is not supported as a regular behavior, only during tests to make them predictable (ie e2e profile)."
    );
  }

  @Override
  public void unfreeze() {
    throw new UnsupportedOperationException(
      "Unfreezing clock is not supported as a regular behavior, only during tests to make them predictable (ie e2e profile)."
    );
  }

  @Override
  public Instant now() {
    return Instant.now();
  }
}
