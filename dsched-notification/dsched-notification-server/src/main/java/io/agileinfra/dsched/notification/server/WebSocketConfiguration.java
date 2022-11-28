package io.agileinfra.dsched.notification.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Import(RestConfiguration.class)
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

  private final ObjectMapper objectMapper;

  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry
      .addEndpoint("/ws/v1/scheduled-notifications")
      .setAllowedOrigins("http://localhost:5000", "http://localhost:7000", "http://localhost:9080")
      .withSockJS();
  }

  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic/");
    registry.setPreservePublishOrder(true);
  }

  public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
    var converter = new MappingJackson2MessageConverter();
    // Avoid creating many ObjectMappers which have the same configuration.
    converter.setObjectMapper(objectMapper);
    messageConverters.add(converter);

    // Don't add default converters.
    return false;
  }
}
