package io.agileinfra.dsched.broker.e2e.tests;

import io.agileinfra.dsched.broker.e2e.consumer.client.E2EBrokerConsumerClientConfiguration;
import io.agileinfra.dsched.broker.e2e.producer.client.E2EBrokerProducerClientConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({ E2EBrokerProducerClientConfiguration.class, E2EBrokerConsumerClientConfiguration.class })
public class E2EBrokerCucumberTestConfiguration {}
