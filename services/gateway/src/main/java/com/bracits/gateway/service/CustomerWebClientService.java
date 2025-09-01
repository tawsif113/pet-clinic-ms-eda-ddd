package com.bracits.gateway.service;

import com.bracits.gateway.dto.OwnerRequestDto;
import com.bracits.sharedevent.dto.*;
import com.bracits.sharedevent.exception.NotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerWebClientService {

    private final CustomerFeignClient customerFeignClient;

    public OwnerPageDto listOwners(int page, int size) {
        try {
            return customerFeignClient.getAllOwners(page, size);
        } catch (FeignException e) {
            log.error("Failed to fetch owners (page={}, size={}) from owner-query-service: {}",
                    page, size, e.getMessage(), e);
            return new OwnerPageDto(List.of(), page, size, 0L, 0, true);
        }
    }

    public OwnerResponseDto getOwnerById(Long ownerId) {
        try {
            return customerFeignClient.getOwnerById(ownerId);
        } catch (FeignException.NotFound e) {
            log.warn("Owner not found: {}", ownerId);
            throw new NotFoundException("Owner not found: " + ownerId);
        } catch (FeignException e) {
            log.error("Owner service error while fetching id {}: {}", ownerId, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch owner: " + ownerId, e);
        }
    }

    public List<PetResponseDto> getOwnerWithPets(Long ownerId) {
        try {
            return customerFeignClient.getOwnerWithPets(ownerId);
        } catch (FeignException.NotFound e) {
            log.warn("Owner (with pets) not found: {}", ownerId);
            throw new NotFoundException("Owner not found: " + ownerId);
        } catch (FeignException e) {
            log.error("Owner service error while fetching owner with pets {}: {}", ownerId, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch owner with pets: " + ownerId, e);
        }
    }
    public PetResponseDto getPetById(Long petId) {
        try {
            return customerFeignClient.getPetById(petId);
        } catch (FeignException.NotFound e) {
            log.warn("Pet not found: {}", petId);
            throw new NotFoundException("Pet not found: " + petId);
        } catch (FeignException e) {
            log.error("Owner service error while fetching pet {}: {}", petId, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch pet: " + petId, e);
        }
    }
}
