package com.bracits.customercommand.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="owners")
public class Owner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name="owner_id")
  private Set<Pet> pets = new HashSet<>();

  public static Owner create(String name, String email) {
    Owner owner = new Owner();
    owner.name = name;
    owner.email = email;
    return owner;
  }

  public void addPet(String name, String species) {
    Pet pet = new Pet(name, species);
    pets.add(pet);
  }

  public void update(String name, String email) { this.name = name; this.email = email; }

  public void updatePet(Long petId, String name, String species)
  {
    this.getPets().stream().filter(o -> o.getId().equals(petId)).findFirst().ifPresent(pet -> {
      pet.setName(name);
      pet.setSpecies(species);
    });
  }

}
