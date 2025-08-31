package com.bracits.customercommand.application.commandhandler;

import com.bracits.customercommand.application.command.UpdatePetCommand;
import com.bracits.customercommand.domain.model.Owner;
import com.bracits.customercommand.domain.model.Pet;
import com.bracits.customercommand.domain.repository.OwnerRepository;
import com.bracits.customercommand.infrastructure.messaging.publisher.EventPublisher;
import com.bracits.sharedevent.event.customer.PetUpdatedEvent;
import com.bracits.sharedevent.messaging.RabbitMQConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UpdatePetCommandHandler {

    private final OwnerRepository ownerRepository;
    private final EventPublisher eventPublisher;

    public void handle(UpdatePetCommand updatePetCommand) {

        Owner owner = ownerRepository.findById(updatePetCommand.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        owner.updatePet(updatePetCommand.petId(), updatePetCommand.name(), updatePetCommand.species());
        owner = ownerRepository.saveAndFlush(owner);

        PetUpdatedEvent petUpdatedEvent = new PetUpdatedEvent(
                owner.getId(),
                owner.getName(),
                owner.getEmail(),
                updatePetCommand.name(),
                updatePetCommand.species(),
                updatePetCommand.petId(),
                updatePetCommand.correlationId(),
                updatePetCommand.correlationId(),
                Instant.now()
        );

        eventPublisher.publishEvent(RabbitMQConstants.CUSTOMER_EXCHANGE, RabbitMQConstants.PET_UPDATED_QUERY_ROUTING_KEY,
                petUpdatedEvent);

    }

}
