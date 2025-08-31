package com.bracits.customerquery.application.service;

import com.bracits.customerquery.domain.model.OwnerEntity;
import com.bracits.customerquery.domain.repository.OwnerReadRepository;
import com.bracits.sharedevent.event.customer.PetCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePetEventHandler {

    private final OwnerReadRepository ownerReadRepository;

    public void handle(PetCreatedEvent petCreatedEvent){
        OwnerEntity owner = ownerReadRepository.findById(petCreatedEvent.ownerId()).orElseThrow(()-> new RuntimeException("Owner not found with id: " + petCreatedEvent.ownerId()));
        owner.addPet(petCreatedEvent.petId(), petCreatedEvent.petName());
        ownerReadRepository.save(owner);
    }

}
