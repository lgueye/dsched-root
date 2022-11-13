package io.agileinfra.dsched.notification.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.dsched.broker.consumer.IncomingMessageProcessor;
import io.agileinfra.dsched.broker.consumer.ScheduledTasksNotificationsBrokerConsumerConfiguration;
import io.agileinfra.dsched.clock.api.ClockApiConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
@Import({ ScheduledTasksNotificationsBrokerConsumerConfiguration.class, ClockApiConfiguration.class })
@Slf4j
public class NotificationServerConfiguration {

  @Bean
  IncomingMessageProcessor notificationService(final ObjectMapper objectMapper, final SimpMessagingTemplate simpMessagingTemplate) {
    return new NotificationService(objectMapper, simpMessagingTemplate);
  }
}
