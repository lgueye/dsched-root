package io.agileinfra.dsched.notification.e2e.tests;

import io.agileinfra.dsched.broker.producer.BrokerProducerConfiguration;
import io.agileinfra.dsched.notification.api.NotificationApiConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({ NotificationApiConfiguration.class, BrokerProducerConfiguration.class })
public class E2ENotificationCucumberTestConfiguration {}
