package com.edapoc.customerquery.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pets_read")
public class PetEntity{

    @Id
    private Long id;

    private String name;
    private String species;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;
}
