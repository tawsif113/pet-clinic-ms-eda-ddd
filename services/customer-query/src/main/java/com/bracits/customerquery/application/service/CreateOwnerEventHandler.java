package com.bracits.customerquery.application.service;

import com.bracits.customerquery.domain.model.OwnerEntity;
import com.bracits.customerquery.domain.repository.OwnerReadRepository;
import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOwnerEventHandler {
    private final OwnerReadRepository ownerReadRepository;

    public void handle(OwnerCreatedEvent ownerCreatedEvent) {
        OwnerEntity owner = OwnerEntity.createOwner(ownerCreatedEvent.ownerId(), ownerCreatedEvent.name(), ownerCreatedEvent.email());
        ownerReadRepository.save(owner);
    }
}
