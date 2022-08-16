package io.agileinfra.dsched.scheduler.producer.server;

import io.agileinfra.dsched.broker.producer.BrokerProducerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BrokerProducerConfiguration.class)
public class SchedulerProducerServerConfiguration {}
