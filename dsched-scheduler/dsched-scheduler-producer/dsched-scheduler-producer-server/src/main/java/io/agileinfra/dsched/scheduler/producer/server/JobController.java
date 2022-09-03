package io.agileinfra.dsched.scheduler.producer.server;

import io.agileinfra.dsched.clock.model.ClockApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trigger-me")
@RequiredArgsConstructor
@Slf4j
public class JobController {

  private final ClockApi clockApi;

  @PostMapping
  public void triggerMe() {
    log.info("Successfully triggered at {}", clockApi.now());
  }
}
