package io.agileinfra.dsched.broker.producer;

import io.agileinfra.dsched.broker.configuration.BrokerConfiguration;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@Import(BrokerConfiguration.class)
public class BrokerProducerConfiguration {

  @Bean
  JmsTemplate jmsTopicTemplate(final ConnectionFactory connectionFactory, final MessageConverter messageConverter) {
    var jmsTemplate = new JmsTemplate(connectionFactory);
    jmsTemplate.setMessageConverter(messageConverter);
    jmsTemplate.setPubSubDomain(true);
    return jmsTemplate;
  }

  @Bean
  BrokerProducer brokerProducer(final JmsTemplate jmsTemplate) {
    return new BrokerProducer(jmsTemplate);
  }
}
