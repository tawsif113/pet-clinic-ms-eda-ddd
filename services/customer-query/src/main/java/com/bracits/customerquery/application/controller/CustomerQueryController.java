package com.bracits.customerquery.application.controller;

import com.bracits.customerquery.application.dto.OwnerResponse;
import com.bracits.customerquery.application.query.GetAllOwnerDetailsQuery;
import com.bracits.customerquery.application.query.GetOwnerByIdQuery;
import com.bracits.customerquery.application.query.GetOwnerWithPetsQuery;
import com.bracits.customerquery.application.query.GetPetByIdQuery;
import com.bracits.customerquery.application.queryhandler.GetAllOwnerDetailsQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetOwnerByIdQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetOwnerWithPetsQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetPetByIdQueryHandler;
import com.bracits.customerquery.domain.model.OwnerReadModel;
import com.bracits.customerquery.domain.model.PetReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer-service/query")
public class CustomerQueryController {

    private final GetAllOwnerDetailsQueryHandler getAllOwnerDetailsQueryHandler;
    private final GetOwnerByIdQueryHandler getOwnerByIdQueryHandler;
    private final GetOwnerWithPetsQueryHandler getOwnerWithPetsQueryHandler;
    private final GetPetByIdQueryHandler getPetByIdQueryHandler;


    @GetMapping("/owners")
    public ResponseEntity<Page<OwnerReadModel>> getAllOwners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        GetAllOwnerDetailsQuery query = new GetAllOwnerDetailsQuery(page, size);
        Page<OwnerReadModel> owners = getAllOwnerDetailsQueryHandler.query(query);
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable Long ownerId) {
        GetOwnerByIdQuery query = new GetOwnerByIdQuery(ownerId);
        OwnerResponse owner = getOwnerByIdQueryHandler.query(query);
        return ResponseEntity.ok(owner);
    }

    @GetMapping("/owners/{ownerId}/pets")
    public ResponseEntity<OwnerReadModel> getOwnerWithPets(@PathVariable Long ownerId) {
        GetOwnerWithPetsQuery query = new GetOwnerWithPetsQuery(ownerId);
        OwnerReadModel owner = getOwnerWithPetsQueryHandler.query(query);
        return ResponseEntity.ok(owner);
    }

    @GetMapping("/pets/{petId}")
    public ResponseEntity<PetReadModel> getPetById(@PathVariable Long petId) {
        GetPetByIdQuery getPetByIdQuery = new GetPetByIdQuery(petId);
        PetReadModel pet = getPetByIdQueryHandler.query(getPetByIdQuery);
        return ResponseEntity.ok(pet);
    }

}
