package io.agileinfra.dsched.broker.server;

import io.agileinfra.dsched.broker.configuration.BrokerConfiguration;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BrokerConfiguration.class)
public class BrokerServerConfiguration {

  @Bean
  public BrokerService brokerService(final ActiveMQProperties activeMQProperties) throws Exception {
    var broker = new BrokerService();
    broker.addConnector(activeMQProperties.getBrokerUrl());
    broker.setPersistent(false);
    broker.setUseJmx(false);
    broker.start();
    return broker;
  }
}
