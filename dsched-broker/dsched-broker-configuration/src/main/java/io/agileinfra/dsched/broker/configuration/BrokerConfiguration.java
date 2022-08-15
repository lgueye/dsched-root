package io.agileinfra.dsched.broker.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@Import(RestConfiguration.class)
@EnableConfigurationProperties({ DestinationsProperties.class, ActiveMQProperties.class })
public class BrokerConfiguration {

  @Bean
  ConnectionFactory connectionFactory(final ActiveMQProperties activeMQProperties) {
    return new ActiveMQConnectionFactory(activeMQProperties.getBrokerUrl());
  }

  @Bean
  MessageConverter converter(final ObjectMapper objectMapper) {
    var converter = new MappingJackson2MessageConverter();
    converter.setObjectMapper(objectMapper);
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }
}
