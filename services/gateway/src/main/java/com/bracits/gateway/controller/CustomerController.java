package com.bracits.gateway.controller;

import com.bracits.gateway.dto.OwnerRequestDto;
import com.bracits.gateway.service.CustomerWebClientService;
import com.bracits.gateway.service.OwnerService;
import com.bracits.sharedevent.dto.OwnerPageDto;
import com.bracits.sharedevent.dto.OwnerResponseDto;
import com.bracits.sharedevent.dto.PetResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerWebClientService customerWebClientService;
  private final OwnerService ownerService;

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
  public ResponseEntity<OwnerResponseDto> createOwner(@RequestBody OwnerRequestDto ownerRequestDto)
      throws Exception {
    return ResponseEntity.ok(ownerService.createOwner(ownerRequestDto));
  }

}
