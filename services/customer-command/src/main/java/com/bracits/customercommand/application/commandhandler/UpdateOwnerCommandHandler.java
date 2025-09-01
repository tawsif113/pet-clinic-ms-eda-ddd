package com.bracits.customercommand.application.commandhandler;

import com.bracits.customercommand.application.command.UpdateOwnerCommand;
import com.bracits.customercommand.domain.model.Owner;
import com.bracits.customercommand.domain.repository.OwnerRepository;
import com.bracits.customercommand.infrastructure.messaging.publisher.EventPublisher;
import com.bracits.sharedevent.event.customer.OwnerUpdatedEvent;
import com.bracits.sharedevent.messaging.RabbitMQConstants;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOwnerCommandHandler implements CommandHandler<UpdateOwnerCommand> {

  private final OwnerRepository ownerRepository;
  private final EventPublisher eventPublisher;

  public void handle(UpdateOwnerCommand updateOwnerCommand) {
    Owner owner = ownerRepository.findById(updateOwnerCommand.ownerId())
        .orElseThrow(() -> new RuntimeException("Owner not found"));
    owner.update(updateOwnerCommand.name(), updateOwnerCommand.email());
    ownerRepository.save(owner);

    OwnerUpdatedEvent event = new OwnerUpdatedEvent(
        owner.getId(),
        owner.getName(),
        owner.getEmail(),
        UUID.randomUUID().toString(),
        updateOwnerCommand.correlationId(),
        Instant.now()
    );
    eventPublisher.publishEvent(RabbitMQConstants.CUSTOMER_EXCHANGE,
        RabbitMQConstants.OWNER_UPDATED_QUERY_ROUTING_KEY,
        event);
  }

  @Override
  public Class<UpdateOwnerCommand> getCommandType() {
    return UpdateOwnerCommand.class;
  }
}