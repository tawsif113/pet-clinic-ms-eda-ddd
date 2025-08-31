package com.bracits.customerquery.domain.repository;

import com.bracits.customerquery.application.dto.OwnerResponse;
import com.bracits.customerquery.domain.model.OwnerEntity;
import com.bracits.customerquery.domain.model.OwnerReadModel;
import com.bracits.customerquery.domain.model.PetReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OwnerReadRepository extends JpaRepository<OwnerEntity, Long> {

    @Query("""
        SELECT new com.bracits.customerquery.application.dto.OwnerResponse(
            o.id,
            o.name,
            o.email
        )
        FROM OwnerEntity o
        WHERE o.id = :id
    """)
    Optional<OwnerResponse> findOwnerById(@Param("id") Long id);

    @Query("""
        SELECT new com.bracits.customerquery.application.dto.OwnerResponse(
            o.id,
            o.name,
            o.email
        )
        FROM OwnerEntity o
        WHERE
            (:name IS NULL OR o.name LIKE CONCAT('%', :name, '%')) AND
            (:email IS NULL OR o.email LIKE CONCAT('%', :email, '%'))
        ORDER BY o.name
    """)
    Page<OwnerResponse> findByNameAndEmail(@Param("name") String name,
                                           @Param("email") String email,
                                           Pageable pageable);

    Page<OwnerReadModel> findAllOwnersWithPets(Pageable pageable);

    Optional<OwnerReadModel> findOwnerWithPetsById(@Param("id") Long id);
}
