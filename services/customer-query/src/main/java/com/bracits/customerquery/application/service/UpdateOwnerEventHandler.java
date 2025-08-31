package com.bracits.customerquery.application.service;

import com.bracits.customerquery.domain.model.OwnerEntity;
import com.bracits.customerquery.domain.repository.OwnerReadRepository;
import com.bracits.sharedevent.event.customer.OwnerUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOwnerEventHandler {

    private final OwnerReadRepository ownerReadRepository;

    public void handle(OwnerUpdatedEvent ownerUpdatedEvent) {
        OwnerEntity owner = ownerReadRepository.findById(ownerUpdatedEvent.ownerId()).orElseThrow(() ->
                new RuntimeException("Owner not found with id: " + ownerUpdatedEvent.ownerId()));
        owner.updateOwner(ownerUpdatedEvent.name(), ownerUpdatedEvent.email());
        ownerReadRepository.save(owner);
    }
}
