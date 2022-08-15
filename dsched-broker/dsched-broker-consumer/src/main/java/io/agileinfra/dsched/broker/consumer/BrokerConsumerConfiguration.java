package io.agileinfra.dsched.broker.consumer;

import io.agileinfra.dsched.broker.configuration.BrokerConfiguration;
import java.util.List;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@Import(BrokerConfiguration.class)
@EnableJms
public class BrokerConsumerConfiguration {

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(final ConnectionFactory connectionFactory) {
    var factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setConcurrency("1-1");
    factory.setPubSubDomain(true);
    return factory;
  }

  @Bean
  public BrokerReactor brokerReactor(final List<IncomingMessageProcessor> processors) {
    return new BrokerReactor(processors);
  }

  @Bean
  public BrokerConsumer brokerConsumer(final BrokerReactor brokerReactor) {
    return new BrokerConsumer(brokerReactor);
  }
}
