package io.agileinfra.dsched.clock.server;

import io.agileinfra.dsched.clock.model.ClockApi;
import io.agileinfra.dsched.clock.model.ClockDto;
import java.time.Duration;
import java.time.Instant;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clock")
public class ClockResource implements ClockApi {

  private Instant instant;
  // Should never be null, but ZERO when not set
  private volatile Duration offset = Duration.ZERO;

  @PostMapping("freeze")
  @Override
  public void freeze(@RequestBody ClockDto request) {
    this.instant = request.getInstant();
    offset = Duration.ZERO;
  }

  @PostMapping("unfreeze")
  @Override
  public void unfreeze() {
    if (this.instant != null) {
      this.offset = Duration.between(Instant.now(), this.instant);
      this.instant = null;
    }
  }

  @GetMapping("now")
  public Instant now() {
    if (this.instant != null) {
      return this.instant;
    } else {
      return Instant.now().plus(this.offset);
    }
  }
}
