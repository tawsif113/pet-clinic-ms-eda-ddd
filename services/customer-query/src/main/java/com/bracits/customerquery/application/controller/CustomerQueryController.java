package com.bracits.customerquery.application.controller;

import com.bracits.sharedevent.dto.OwnerResponseDto;
import com.bracits.customerquery.application.query.GetAllOwnerDetailsQuery;
import com.bracits.customerquery.application.query.GetOwnerByIdQuery;
import com.bracits.customerquery.application.query.GetOwnerWithPetsQuery;
import com.bracits.customerquery.application.query.GetPetByIdQuery;
import com.bracits.customerquery.application.queryhandler.GetAllOwnerDetailsQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetOwnerByIdQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetOwnerWithPetsQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetPetByIdQueryHandler;
import com.bracits.sharedevent.dto.OwnerReadResponseDto;
import com.bracits.sharedevent.dto.PetReadResponseDto;
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
    public ResponseEntity<Page<OwnerReadResponseDto>> getAllOwners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        GetAllOwnerDetailsQuery query = new GetAllOwnerDetailsQuery(page, size);
        Page<OwnerReadResponseDto> owners = getAllOwnerDetailsQueryHandler.query(query);
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerResponseDto> getOwnerById(@PathVariable Long ownerId) {
        GetOwnerByIdQuery query = new GetOwnerByIdQuery(ownerId);
        OwnerResponseDto owner = getOwnerByIdQueryHandler.query(query);
        return ResponseEntity.ok(owner);
    }

    @GetMapping("/owners/{ownerId}/pets")
    public ResponseEntity<OwnerReadResponseDto> getOwnerWithPets(@PathVariable Long ownerId) {
        GetOwnerWithPetsQuery query = new GetOwnerWithPetsQuery(ownerId);
        OwnerReadResponseDto owner = getOwnerWithPetsQueryHandler.query(query);
        return ResponseEntity.ok(owner);
    }

    @GetMapping("/pets/{petId}")
    public ResponseEntity<PetReadResponseDto> getPetById(@PathVariable Long petId) {
        GetPetByIdQuery getPetByIdQuery = new GetPetByIdQuery(petId);
        PetReadResponseDto pet = getPetByIdQueryHandler.query(getPetByIdQuery);
        return ResponseEntity.ok(pet);
    }

}
