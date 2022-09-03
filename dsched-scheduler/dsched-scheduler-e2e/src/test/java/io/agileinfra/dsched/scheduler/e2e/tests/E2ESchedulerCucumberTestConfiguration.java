package io.agileinfra.dsched.scheduler.e2e.tests;

import io.agileinfra.dsched.clock.api.ClockApiConfiguration;
import io.agileinfra.dsched.scheduler.consumer.client.SchedulerConsumerClientConfiguration;
import io.agileinfra.dsched.scheduler.producer.client.SchedulerProducerClientConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({ SchedulerProducerClientConfiguration.class, SchedulerConsumerClientConfiguration.class, ClockApiConfiguration.class })
public class E2ESchedulerCucumberTestConfiguration {}
