package com.bracits.customerquery.infrastructure.messaging.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisher {

  private final RabbitTemplate rabbitTemplate;

  public void publishEvent(String exchange, String routingKey, Object event) {
    rabbitTemplate.convertAndSend(exchange, routingKey, event);
  }

}
