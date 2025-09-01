package com.bracits.customerquery.application.queryhandler;

import com.bracits.customerquery.application.query.GetPetByIdQuery;
import com.bracits.customerquery.domain.repository.PetReadRepository;
import com.bracits.sharedevent.dto.PetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPetByIdQueryHandler {

    private final PetReadRepository petReadRepository;

    public PetResponseDto query(GetPetByIdQuery query) {
        return petReadRepository.findPetById(query.petId())
                .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + query.petId()));
    }
}
