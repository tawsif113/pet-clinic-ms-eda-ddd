package com.bracits.sharedevent.dto;

public record CreatePetRequestDto(
        String name,
        String species,
        Long ownerId
) {
}
