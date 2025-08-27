package com.bracits.customercommand.domain.repository;


import com.bracits.customercommand.domain.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
