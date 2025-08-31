package com.bracits.customercommand.application.commandhandler;

import com.bracits.customercommand.application.command.CreatePetCommand;
import com.bracits.sharedevent.event.customer.PetCreatedEvent;
import java.time.Instant;
import java.util.UUID;

import com.bracits.sharedevent.event.RabbitMQConstants;
import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import com.bracits.sharedevent.event.customer.OwnerUpdatedEvent;
import com.bracits.customercommand.application.command.CreateOwnerCommand;
import com.bracits.customercommand.application.command.UpdateOwnerCommand;
import com.bracits.customercommand.domain.model.Owner;
import com.bracits.customercommand.domain.repository.OwnerRepository;
import com.bracits.customercommand.infrastructure.messaging.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerCommandHandler {

  private final OwnerRepository ownerRepository;
  private final EventPublisher eventPublisher;

  public void handle(CreateOwnerCommand createOwnerCommand) {
    Owner owner = Owner.create(createOwnerCommand.name(), createOwnerCommand.email());
    ownerRepository.save(owner);

    OwnerCreatedEvent event = new OwnerCreatedEvent(
        owner.getId(),
        owner.getName(),
        owner.getEmail(),
        UUID.randomUUID().toString(),
        createOwnerCommand.correlationId(),
        Instant.now()
    );
    eventPublisher.publishEvent(
            RabbitMQConstants.CUSTOMER_EXCHANGE,
            RabbitMQConstants.OWNER_CREATED_QUERY_ROUTING_KEY,
            event
    );
  }

  public void handle(UpdateOwnerCommand updateOwnerCommand) {
    Owner owner = ownerRepository.findById(updateOwnerCommand.ownerId()).orElseThrow(() -> new RuntimeException("Owner not found"));
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

  public void handle(CreatePetCommand createPetCommand){
    Owner owner = ownerRepository.findById(createPetCommand.ownerId()).orElseThrow(() -> new RuntimeException("Owner not found"));
    owner.addPet(createPetCommand.name(), createPetCommand.species());
    ownerRepository.save(owner);

    PetCreatedEvent event = new PetCreatedEvent(
        owner.getId(),
        owner.getName(),
        owner.getEmail(),
        1L,
        UUID.randomUUID().toString(),
        "10",
        Instant.now(),
        createPetCommand.name()
    );

    eventPublisher.publishEvent(RabbitMQConstants.CUSTOMER_EXCHANGE,
        RabbitMQConstants.PET_CREATED_ROUTING_KEY,
        event);
  }
}

