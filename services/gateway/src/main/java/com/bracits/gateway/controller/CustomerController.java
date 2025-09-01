package com.bracits.gateway.controller;

import com.bracits.gateway.dto.OwnerRequestDto;
import com.bracits.gateway.service.CustomerWebClientService;
import com.bracits.sharedevent.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerWebClientService customerWebClientService;

    @GetMapping("/owners")
    public ResponseEntity<OwnerPageDto> getAllOwners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(customerWebClientService.listOwners(page, size));
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerResponseDto> getOwnerById(@PathVariable Long ownerId) {
        return ResponseEntity.ok(customerWebClientService.getOwnerById(ownerId));
    }

    @GetMapping("/owners/{ownerId}/pets")
    public ResponseEntity<List<PetResponseDto>> getOwnerWithPets(@PathVariable Long ownerId) {
        return ResponseEntity.ok(customerWebClientService.getOwnerWithPets(ownerId));
    }

    @GetMapping("/pets/{petId}")
    public ResponseEntity<PetResponseDto> getPetById(@PathVariable Long petId) {
        return ResponseEntity.ok(customerWebClientService.getPetById(petId));
    }

    @PostMapping("/owners")
    public ResponseEntity<OwnerResponseDto> createOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        return null;
    }

}
