package io.agileinfra.dsched.scheduler.e2e.tests.steps;

import io.agileinfra.dsched.clock.model.ClockApi;
import io.agileinfra.dsched.clock.model.ClockDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class E2EClockSteps {

  private final ClockApi clockApi;

  @Given("clock is {string}")
  public void clock_is(String isoInstant) {
    clockApi.freeze(ClockDto.builder().instant(Instant.parse(isoInstant)).build());
  }

  @When("unfreeze clock")
  public void unfreeze_clock() {
    clockApi.unfreeze();
  }
}
