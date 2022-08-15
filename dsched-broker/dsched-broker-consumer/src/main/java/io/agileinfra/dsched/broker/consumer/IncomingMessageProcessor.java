package io.agileinfra.dsched.broker.consumer;

public interface IncomingMessageProcessor {
  boolean accept(final ProcessingContext context);
  void process(final ProcessingContext context);
}
