package com.bracits.customerquery.application.service;

import com.bracits.customerquery.domain.model.OwnerEntity;
import com.bracits.customerquery.domain.repository.OwnerReadRepository;
import com.bracits.sharedevent.event.customer.PetUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePetEventHandler {

    private final OwnerReadRepository ownerReadRepository;

    public void handle(PetUpdatedEvent petUpdatedEvent){
        OwnerEntity owner = ownerReadRepository.findById(petUpdatedEvent.ownerId()).orElseThrow(()-> new RuntimeException("Owner not found with id: " + petUpdatedEvent.ownerId()));
        owner.updatePet(petUpdatedEvent.petId(), petUpdatedEvent.petName(), petUpdatedEvent.petSpecies());
        ownerReadRepository.save(owner);
    }

}
