package com.bracits.customerquery.application.queryhandler;

import com.bracits.customerquery.application.query.GetAllOwnerDetailsQuery;
import com.bracits.customerquery.domain.model.OwnerReadModel;
import com.bracits.customerquery.domain.repository.OwnerReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllOwnerDetailsQueryHandler {
    private final OwnerReadRepository ownerReadRepository;

    public Page<OwnerReadModel> query(GetAllOwnerDetailsQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return ownerReadRepository.findAllOwnersWithPets(pageable);
    }
}
