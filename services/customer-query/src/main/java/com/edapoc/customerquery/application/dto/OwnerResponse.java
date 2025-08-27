package com.edapoc.customerquery.application.dto;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OwnerResponse{
    private Long id;
    private String name;
    private String email;
    private List<PetResponse> pets;

    public OwnerResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public OwnerResponse(Long id, String name, String email, List<PetResponse> pets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pets = pets;
    }
}
