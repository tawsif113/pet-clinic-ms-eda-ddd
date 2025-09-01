package com.bracits.customerquery.application.queryhandler;

import com.bracits.customerquery.application.query.GetOwnerWithPetsQuery;
import com.bracits.customerquery.domain.repository.PetReadRepository;
import com.bracits.sharedevent.dto.PetReadResponseDto;
import com.bracits.sharedevent.dto.PetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOwnerWithPetsQueryHandler {

    private final PetReadRepository petReadRepository;

    public List<PetResponseDto> query(GetOwnerWithPetsQuery query) {
        return petReadRepository.findByOwnerId(query.ownerId());
    }
}
