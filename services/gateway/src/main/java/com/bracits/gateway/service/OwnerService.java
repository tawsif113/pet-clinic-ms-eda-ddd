package com.bracits.gateway.service;

import com.bracits.gateway.consumer.EventReplyListener;
import com.bracits.gateway.dto.OwnerRequestDto;
import com.bracits.gateway.publisher.EventPublisher;
import com.bracits.sharedevent.dto.OwnerResponseDto;
import com.bracits.sharedevent.event.customer.OwnerCreateRequestEvent;
import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import com.bracits.sharedevent.messaging.RabbitMQConstants;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

  private final EventPublisher eventPublisher;
  private final EventReplyListener replyListener;

  public OwnerResponseDto createOwner(OwnerRequestDto ownerRequestDto) throws Exception {
    String correlationId = UUID.randomUUID().toString();

    eventPublisher.publishEvent(
        RabbitMQConstants.CUSTOMER_EXCHANGE,
        RabbitMQConstants.OWNER_CREATE_COMMAND_ROUTING_KEY,
        new OwnerCreateRequestEvent(ownerRequestDto.name(), ownerRequestDto.email(), correlationId)
    );

    OwnerCreatedEvent reply = replyListener.waitForReply(correlationId, 5000);

    if (reply == null) {
      throw new Exception("Timeout waiting for reply");
    }

    return new OwnerResponseDto(reply.ownerId(), reply.name(), reply.email());
  }


}
