package com.bracits.customercommand.domain.repository;

import com.bracits.customercommand.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Long>{
}
