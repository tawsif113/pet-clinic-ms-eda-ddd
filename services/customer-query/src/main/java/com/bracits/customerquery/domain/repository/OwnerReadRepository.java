package com.bracits.customerquery.domain.repository;

import com.bracits.customerquery.domain.model.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerReadRepository extends JpaRepository<OwnerEntity, Long> {

//    @Query("""
//        SELECT new com.edapoc.customerquery.application.dto.OwnerResponse(
//            o.id,
//            o.name,
//            o.email
//        )
//        FROM OwnerEntity o
//        WHERE o.id = :id
//    """)
//    Optional<OwnerResponse> findOwnerById(@Param("id") Long id);

    // Find owners by name and/or email with pagination
//    @Query("""
//        SELECT new com.edapoc.customerquery.application.dto.OwnerResponse(
//            o.id,
//            o.name,
//            o.email
//        )
//        FROM OwnerEntity o
//        WHERE
//            (:name IS NULL OR o.name LIKE CONCAT('%', :name, '%')) AND
//            (:email IS NULL OR o.email LIKE CONCAT('%', :email, '%'))
//        ORDER BY o.name
//    """)
//    Page<OwnerResponse> findByNameAndEmail(@Param("name") String name,
//                                           @Param("email") String email,
//                                           Pageable pageable);

    // Get pets for a specific owner
//    @Query("""
//        SELECT new com.edapoc.customerquery.application.dto.PetResponse(
//            p.id,
//            p.name,
//            p.species
//        )
//        FROM PetEntity p
//        WHERE p.owner.id = :ownerId
//        ORDER BY p.id
//    """)
//    List<PetResponse> findPetsByOwnerId(@Param("ownerId") Long ownerId);

//    @Query("""
//        SELECT new com.edapoc.customerquery.application.dto.OwnerResponse(
//            o.id,
//            o.name,
//            o.email,
//            new com.edapoc.customerquery.application.dto.PetResponse(
//                p.id,
//                p.name,
//                p.species
//            )
//        )
//        FROM OwnerEntity o
//        LEFT JOIN o.pets p
//        ORDER BY o.id
//    """)
//    Page<OwnerResponse> findAllOwnersWithPets(Pageable pageable);
//
//    @Query("""
//        SELECT new com.edapoc.customerquery.application.dto.OwnerResponse(
//            o.id,
//            o.name,
//            o.email,
//            new com.edapoc.customerquery.application.dto.PetResponse(
//                p.id,
//                p.name,
//                p.species
//            )
//        )
//        FROM OwnerEntity o
//        LEFT JOIN o.pets p
//        WHERE o.id = :id
//        ORDER BY o.id
//    """)
//    Optional<OwnerResponse> findOwnerWithPetsById(@Param("id") Long id);
}
