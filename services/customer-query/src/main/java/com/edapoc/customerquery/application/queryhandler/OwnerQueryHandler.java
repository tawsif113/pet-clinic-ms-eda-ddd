package com.edapoc.customerquery.application.queryhandler;

import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import com.bracits.sharedevent.event.customer.OwnerUpdatedEvent;
import com.edapoc.customerquery.application.dto.OwnerResponse;
import com.edapoc.customerquery.application.query.FindOwnersQuery;
import com.edapoc.customerquery.application.query.GetOwnerByIdQuery;
import com.edapoc.customerquery.application.query.GetOwnerWithPetsQuery;
import com.edapoc.customerquery.domain.model.OwnerEntity;
import com.edapoc.customerquery.domain.repository.OwnerReadRepository;
import com.edapoc.customerquery.infrastructure.messaging.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerQueryHandler{

    private final OwnerReadRepository ownerReadRepository;
    private final EventPublisher eventPublisher;

//    public void handle(GetOwnerByIdQuery query) {
//        OwnerResponse ownerResponse=  ownerReadRepository.findOwnerById(query.ownerId())
//                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + query.ownerId()));
//    }
//
//    public  void handle(FindOwnersQuery query) {
//        Pageable pageable = PageRequest.of(query.page(), query.size());
//        Page<OwnerResponse> owners;
//        if (query.name() != null || query.email() != null) {
//            owners = ownerReadRepository.findByNameAndEmail(
//                    query.name(), query.email(), pageable);
//        }
//         else owners = ownerReadRepository.findAllOwnersWithPets(pageable);
//
//         // publish event
//    }
//
//    public void handle(GetOwnerWithPetsQuery query) {
//
//        OwnerResponse  owner = ownerReadRepository.findOwnerWithPetsById(query.ownerId())
//                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + query.ownerId()));
//        // publish event
//    }

    public void handle(OwnerCreatedEvent ownerCreatedEvent) {
        OwnerEntity owner = OwnerEntity.createOwner(ownerCreatedEvent.ownerId(), ownerCreatedEvent.name(), ownerCreatedEvent.email());
        ownerReadRepository.save(owner);
    }

//    public void handle(OwnerUpdatedEvent ownerUpdatedEvent) {
//        OwnerEntity owner = ownerReadRepository.findById(ownerUpdatedEvent.ownerId())
//                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerUpdatedEvent.ownerId()));
//        owner.updateOwner(ownerUpdatedEvent.name(), ownerUpdatedEvent.email());
//        ownerReadRepository.save(owner);
//
//        // publish an event
//    }
}
