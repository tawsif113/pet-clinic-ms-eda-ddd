package com.bracits.customerquery.application.controller;

import com.bracits.sharedevent.dto.*;
import com.bracits.customerquery.application.query.GetAllOwnerDetailsQuery;
import com.bracits.customerquery.application.query.GetOwnerByIdQuery;
import com.bracits.customerquery.application.query.GetOwnerWithPetsQuery;
import com.bracits.customerquery.application.query.GetPetByIdQuery;
import com.bracits.customerquery.application.queryhandler.GetAllOwnerDetailsQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetOwnerByIdQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetOwnerWithPetsQueryHandler;
import com.bracits.customerquery.application.queryhandler.GetPetByIdQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer-service/query")
public class CustomerQueryController {

    private final GetAllOwnerDetailsQueryHandler getAllOwnerDetailsQueryHandler;
    private final GetOwnerByIdQueryHandler getOwnerByIdQueryHandler;
    private final GetOwnerWithPetsQueryHandler getOwnerWithPetsQueryHandler;
    private final GetPetByIdQueryHandler getPetByIdQueryHandler;


    @GetMapping("/owners")
    public ResponseEntity<OwnerPageDto> getAllOwners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        GetAllOwnerDetailsQuery query = new GetAllOwnerDetailsQuery(page, size);
        Page<OwnerResponseDto> owners = getAllOwnerDetailsQueryHandler.query(query);
        List<OwnerResponseDto> ownerDtos = owners.getContent().stream()
                .map(o -> new OwnerResponseDto(o.getId(), o.getName(), o.getEmail()))
                .toList();

        OwnerPageDto ownerPageDto = new OwnerPageDto(
                ownerDtos,
                owners.getNumber(),
                owners.getSize(),
                owners.getTotalElements(),
                owners.getTotalPages(),
                owners.isLast()
        );
        return ResponseEntity.ok(ownerPageDto);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerResponseDto> getOwnerById(@PathVariable Long ownerId) {
        GetOwnerByIdQuery query = new GetOwnerByIdQuery(ownerId);
        OwnerResponseDto owner = getOwnerByIdQueryHandler.query(query);
        return ResponseEntity.ok(owner);
    }

    @GetMapping("/owners/{ownerId}/pets")
    public ResponseEntity<List<PetResponseDto>> getOwnerWithPets(@PathVariable Long ownerId) {
        GetOwnerWithPetsQuery query = new GetOwnerWithPetsQuery(ownerId);
        List<PetResponseDto> pets = getOwnerWithPetsQueryHandler.query(query);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/pets/{petId}")
    public ResponseEntity<PetResponseDto> getPetById(@PathVariable Long petId) {
        GetPetByIdQuery getPetByIdQuery = new GetPetByIdQuery(petId);
        PetResponseDto pet = getPetByIdQueryHandler.query(getPetByIdQuery);
        return ResponseEntity.ok(pet);
    }

}
