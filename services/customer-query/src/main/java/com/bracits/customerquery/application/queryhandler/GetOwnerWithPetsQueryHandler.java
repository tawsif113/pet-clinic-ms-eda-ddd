package com.bracits.customerquery.application.queryhandler;

import com.bracits.customerquery.application.query.GetOwnerWithPetsQuery;
import com.bracits.customerquery.domain.model.OwnerReadModel;
import com.bracits.customerquery.domain.repository.OwnerReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOwnerWithPetsQueryHandler {

    private final OwnerReadRepository ownerReadRepository;

    public OwnerReadModel query(GetOwnerWithPetsQuery query) {
        return ownerReadRepository.findOwnerWithPetsById(query.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + query.ownerId()));
    }
}
