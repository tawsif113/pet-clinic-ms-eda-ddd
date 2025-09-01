package com.bracits.customercommand.application.commandhandler;

import com.bracits.customercommand.application.command.CreateOwnerCommand;
import com.bracits.customercommand.domain.model.Owner;
import com.bracits.customercommand.domain.repository.OwnerRepository;
import com.bracits.customercommand.infrastructure.messaging.publisher.EventPublisher;
import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import com.bracits.sharedevent.messaging.RabbitMQConstants;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOwnerCommandHandler implements CommandHandler<CreateOwnerCommand> {

  private final OwnerRepository ownerRepository;
  private final EventPublisher eventPublisher;

  @Override
  public void handle(CreateOwnerCommand createOwnerCommand) {
    Owner owner = Owner.create(createOwnerCommand.name(), createOwnerCommand.email());
    ownerRepository.save(owner);

    OwnerCreatedEvent ownerCreatedEvent = new OwnerCreatedEvent(
        owner.getId(),
        owner.getName(),
        owner.getEmail(),
        UUID.randomUUID().toString(),
        createOwnerCommand.correlationId(),
        Instant.now()
    );
    eventPublisher.publishEvent(
        RabbitMQConstants.CUSTOMER_EXCHANGE,
        RabbitMQConstants.OWNER_CREATED_COMMAND_ROUTING_KEY,
        ownerCreatedEvent
    );
    eventPublisher.publishEvent(
            RabbitMQConstants.CUSTOMER_EXCHANGE,
            RabbitMQConstants.OWNER_CREATED_QUERY_ROUTING_KEY,
            ownerCreatedEvent
    );
  }

  @Override
  public Class<CreateOwnerCommand> getCommandType() {
    return CreateOwnerCommand.class;
  }
}