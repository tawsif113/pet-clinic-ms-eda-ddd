package com.bracits.customerquery.domain.repository;

import com.bracits.customerquery.domain.model.PetEntity;
import com.bracits.sharedevent.dto.PetReadResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetReadRepository extends JpaRepository<PetEntity,Long> {

    Optional<PetReadResponseDto> findPetById(Long petId);
}
