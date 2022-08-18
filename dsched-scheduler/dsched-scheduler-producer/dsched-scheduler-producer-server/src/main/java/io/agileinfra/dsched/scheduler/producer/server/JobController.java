package io.agileinfra.dsched.scheduler.producer.server;

import java.time.Instant;
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

  @PostMapping
  public void triggerMe() {
    log.info("Successfully triggered at {}", Instant.now());
  }
}
