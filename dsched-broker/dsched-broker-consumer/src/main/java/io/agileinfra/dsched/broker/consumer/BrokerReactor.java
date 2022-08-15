package io.agileinfra.dsched.broker.consumer;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class BrokerReactor {

  private final List<IncomingMessageProcessor> processors;

  void process(String message, String source) {
    final Instant now = Instant.now();
    final ProcessingContext context = ProcessingContext.builder().source(source).timestamp(now).message(message).build();
    log.info("processing context {} at {}", context, now);
    processors.stream().filter(processor -> processor.accept(context)).forEach(processor -> processor.process(context));
  }
}
