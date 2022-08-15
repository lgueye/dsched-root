package io.agileinfra.dsched.broker.e2e.producer.server;

import io.agileinfra.dsched.broker.producer.BrokerProducer;
import io.agileinfra.dsched.model.ScheduledTask;
import io.agileinfra.dsched.model.ScheduledTaskProducer;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class E2EBrokerProducerScheduleController implements ScheduledTaskProducer {

  private final BrokerProducer producer;

  @PutMapping("{id}")
  public void schedule(@PathVariable("id") final UUID id, @RequestBody final ScheduledTask scheduledTask) {
    // ignore id provided in the body
    var task = scheduledTask.toBuilder().id(id).build();
    producer.scheduleTask(task);
  }
}
