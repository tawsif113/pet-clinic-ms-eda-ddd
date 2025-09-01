package com.bracits.customerquery.domain.repository;

import com.bracits.sharedevent.dto.OwnerResponseDto;
import com.bracits.customerquery.domain.model.OwnerEntity;
import com.bracits.sharedevent.dto.OwnerReadResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OwnerReadRepository extends JpaRepository<OwnerEntity, Long> {

    @Query("""
        SELECT new com.bracits.sharedevent.dto.OwnerResponseDto(
            o.id,
            o.name,
            o.email
        )
        FROM OwnerEntity o
        WHERE o.id = :id
    """)
    Optional<OwnerResponseDto> findOwnerById(@Param("id") Long id);

    @Query("""
        SELECT new com.bracits.sharedevent.dto.OwnerResponseDto(
            o.id,
            o.name,
            o.email
        )
        FROM OwnerEntity o
    """)
    Page<OwnerResponseDto> findAllBy(Pageable pageable);
}
