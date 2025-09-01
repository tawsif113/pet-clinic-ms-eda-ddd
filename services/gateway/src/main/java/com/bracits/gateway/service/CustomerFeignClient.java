package com.bracits.gateway.service;

import com.bracits.gateway.config.OwnerFeignConfig;
import com.bracits.gateway.dto.OwnerRequestDto;
import com.bracits.sharedevent.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "customer-query-client",
        url = "${customer.query.base-url}"
)
public interface CustomerFeignClient{

    @GetMapping("/owners")
    OwnerPageDto getAllOwners(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping("/owners/{ownerId}")
    OwnerResponseDto getOwnerById(@PathVariable("ownerId") Long ownerId);

    @GetMapping("/owners/{ownerId}/pets")
    List<PetResponseDto> getOwnerWithPets(@PathVariable("ownerId") Long ownerId);

    @GetMapping("/pets/{petId}")
    PetResponseDto getPetById(@PathVariable("petId") Long petId);
}
