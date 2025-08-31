package com.bracits.customerquery.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner_read")
@Getter
@Setter
public class OwnerEntity{
    @Id
    private Long id;
    private String name;
    private String email;

    protected OwnerEntity(){}

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetEntity> pets = new ArrayList<>();

    private LocalDateTime lastUpdated;

    public static OwnerEntity createOwner(Long id, String name, String email){
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(id);
        ownerEntity.setEmail(email);
        ownerEntity.setName(name);
        return ownerEntity;
    }

    public void updateOwner(String name,String email){
        this.name = (name != null)?name:this.name;
        this.email = (email != null)?email:this.email;
    }

    public void addPet(Long petId, String petName){
        PetEntity pet = new PetEntity();
        pet.setId(petId);
        pet.setName(petName);
        pet.setOwner(this);
        this.pets.add(pet);
    }

    public void updatePet(Long petId, String petName, String petSpecies){
        PetEntity pet = this.pets.stream().filter(p -> p.getId().equals(petId)).findFirst().orElseThrow(() -> new RuntimeException("Pet not found"));
        pet.setName((petName!=null)?petName:pet.getName());
        pet.setSpecies((petSpecies!=null)?petSpecies:pet.getSpecies());
    }
}