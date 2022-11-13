package io.agileinfra.dsched.broker.consumer;

import io.agileinfra.dsched.broker.configuration.BrokerConfiguration;
import io.agileinfra.dsched.clock.api.ClockApiConfiguration;
import io.agileinfra.dsched.clock.model.ClockApi;
import java.util.List;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@Import({ BrokerConfiguration.class, ClockApiConfiguration.class })
@EnableJms
public class ScheduledTasksNotificationsBrokerConsumerConfiguration {

  @Bean
  DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(final ConnectionFactory connectionFactory) {
    var factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setConcurrency("1-1");
    factory.setPubSubDomain(false);
    return factory;
  }

  @Bean
  BrokerReactor brokerReactor(final List<IncomingMessageProcessor> processors, final ClockApi clockApi) {
    return new BrokerReactor(processors, clockApi);
  }

  @Bean
  ScheduledTasksNotificationsBrokerConsumer scheduledTasksNotificationsBrokerConsumer(BrokerReactor reactor) {
    return new ScheduledTasksNotificationsBrokerConsumer(reactor);
  }
}
