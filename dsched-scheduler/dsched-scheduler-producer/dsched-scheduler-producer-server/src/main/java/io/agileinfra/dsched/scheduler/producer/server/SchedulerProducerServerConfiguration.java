package io.agileinfra.dsched.scheduler.producer.server;

import io.agileinfra.dsched.broker.producer.BrokerProducerConfiguration;
import io.agileinfra.dsched.clock.api.ClockApiConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BrokerProducerConfiguration.class, ClockApiConfiguration.class })
public class SchedulerProducerServerConfiguration {}
