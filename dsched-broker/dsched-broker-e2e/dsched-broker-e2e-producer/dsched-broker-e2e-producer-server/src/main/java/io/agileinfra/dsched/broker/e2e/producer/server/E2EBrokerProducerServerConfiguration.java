package io.agileinfra.dsched.broker.e2e.producer.server;

import io.agileinfra.dsched.broker.producer.BrokerProducerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BrokerProducerConfiguration.class)
public class E2EBrokerProducerServerConfiguration {}
