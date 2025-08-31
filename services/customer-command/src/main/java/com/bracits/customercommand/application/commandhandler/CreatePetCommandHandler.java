package com.bracits.customercommand.application.commandhandler;

import com.bracits.customercommand.application.command.CreatePetCommand;
import com.bracits.customercommand.domain.model.Owner;
import com.bracits.customercommand.domain.model.Pet;
import com.bracits.customercommand.domain.repository.OwnerRepository;
import com.bracits.customercommand.infrastructure.messaging.publisher.EventPublisher;
import com.bracits.sharedevent.messaging.RabbitMQConstants;
import com.bracits.sharedevent.event.customer.PetCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePetCommandHandler {

    private final OwnerRepository ownerRepository;
    private final EventPublisher eventPublisher;

    public void handle(CreatePetCommand createPetCommand){
        Owner owner = ownerRepository.findById(createPetCommand.ownerId()).orElseThrow(() -> new RuntimeException("Owner not found"));
        owner.addPet(createPetCommand.name(), createPetCommand.species());
        owner = ownerRepository.saveAndFlush(owner);

        Pet persistedPet = owner.getPets().stream()
                .filter(p -> p.getName().equals(createPetCommand.name())
                        && p.getSpecies().equals(createPetCommand.species()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pet not found after save"));

        PetCreatedEvent event = new PetCreatedEvent(
                owner.getId(),
                owner.getName(),
                owner.getEmail(),
                persistedPet.getId(),
                UUID.randomUUID().toString(),
                createPetCommand.correlationId(),
                Instant.now(),
                createPetCommand.name()
        );

        eventPublisher.publishEvent(RabbitMQConstants.CUSTOMER_EXCHANGE,
                RabbitMQConstants.PET_CREATED_QUERY_ROUTING_KEY,
                event);
    }

}
