package com.bracits.sharedevent.dto;

public class PetResponseDto {
    private final Long id;
    private final String name;
    private final String species;
    private final Long ownerId;

    public PetResponseDto(Long id, String name, String species, Long ownerId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public Long getOwnerId() {
        return ownerId;
    }

}
