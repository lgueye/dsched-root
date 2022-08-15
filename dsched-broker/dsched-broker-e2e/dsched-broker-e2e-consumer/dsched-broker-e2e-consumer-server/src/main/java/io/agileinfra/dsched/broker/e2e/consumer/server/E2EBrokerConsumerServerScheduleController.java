package io.agileinfra.dsched.broker.e2e.consumer.server;

import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.ScheduledTaskConsumer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class E2EBrokerConsumerServerScheduleController implements ScheduledTaskConsumer {

  private final ScheduledTaskRepository repository;

  @GetMapping
  public List<ScheduledTask> findAllSchedules() {
    return repository.findAll();
  }
}
