package com.bracits.customerquery.domain.repository;

import com.bracits.customerquery.domain.model.PetEntity;
import com.bracits.sharedevent.dto.PetReadResponseDto;
import com.bracits.sharedevent.dto.PetResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetReadRepository extends JpaRepository<PetEntity, Long> {


    @Query("""
                SELECT p.id AS id, p.name AS name, p.species AS species, p.owner.id AS ownerId
                FROM PetEntity p
                WHERE p.id = :petId
            """)
    Optional<PetResponseDto> findPetById(@Param("petId") Long petId);

    @Query("""
                SELECT p.id AS id, p.name AS name, p.species AS species, p.owner.id AS ownerId
                FROM PetEntity p
                WHERE p.owner.id IN :ownerIds
            """)
    List<PetResponseDto> findByOwnerIds(@Param("ownerIds") List<Long> ownerIds);

    @Query("""
                SELECT p.id AS id, p.name AS name, p.species AS species, p.owner.id AS ownerId
                FROM PetEntity p
                WHERE p.owner.id = :ownerId
            """)
    List<PetResponseDto> findByOwnerId(@Param("ownerId") Long ownerId);
}
