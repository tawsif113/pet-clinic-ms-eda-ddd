package com.bracits.customerquery.application.queryhandler;

import com.bracits.sharedevent.dto.OwnerResponseDto;
import com.bracits.customerquery.application.query.GetOwnerByIdQuery;
import com.bracits.customerquery.domain.repository.OwnerReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOwnerByIdQueryHandler {

    private final OwnerReadRepository ownerReadRepository;

    public OwnerResponseDto query(GetOwnerByIdQuery query) {
        return ownerReadRepository.findOwnerById(query.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + query.ownerId()));
    }
}
