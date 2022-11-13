package io.agileinfra.dsched.notification.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.agileinfra.dsched.notification.model.NotificationApi;
import io.agileinfra.dsched.rest.configuration.RestConfiguration;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@Configuration
@Import(RestConfiguration.class)
public class NotificationApiConfiguration {

  @Bean
  public SockJsClient sockJsClient(final RestTemplate restTemplate) {
    var restTemplateXhrTransport = new RestTemplateXhrTransport(restTemplate);
    var webSocketClient = new StandardWebSocketClient();
    var webSocketTransport = new WebSocketTransport(webSocketClient);
    var transports = Lists.newArrayList(webSocketTransport, restTemplateXhrTransport);
    return new SockJsClient(transports);
  }

  @Bean
  public WebSocketStompClient stompClient(final SockJsClient sockJsClient, final ObjectMapper objectMapper) {
    var stompClient = new WebSocketStompClient(sockJsClient);
    var converter = new MappingJackson2MessageConverter();
    converter.setObjectMapper(objectMapper);
    stompClient.setMessageConverter(converter);
    return stompClient;
  }

  @Bean
  public NotificationApi notificationApi(
    @Value("${notification.server.ws.url}") final String apiUrl,
    @Value("${notification.client.ws.connection.timeout:PT1S}") final String connectionTimeout,
    final WebSocketStompClient stompClient
  ) {
    return new NotificationApiImpl(apiUrl, stompClient, Duration.parse(connectionTimeout));
  }
}
