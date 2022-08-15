package io.agileinfra.dsched.broker.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("destinations")
public class DestinationsProperties {

  private final Map<String, String> destinations = new HashMap<>();

  public Map<String, String> getDestinations() {
    return destinations;
  }
}
