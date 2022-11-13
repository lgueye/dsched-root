package io.agileinfra.dsched.broker.consumer;

import io.agileinfra.dsched.clock.model.ClockApi;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class BrokerReactor {

  private final List<IncomingMessageProcessor> processors;
  private final ClockApi clockApi;

  void process(String message, String source) {
    log.info("Successfully received {} from {} at {}", message, source, clockApi.now());
    final Instant now = clockApi.now();
    final ProcessingContext context = ProcessingContext.builder().source(source).timestamp(now).message(message).build();
    log.info("processing context {} at {}", context, now);
    processors.stream().filter(processor -> processor.accept(context)).forEach(processor -> processor.process(context));
  }
}
