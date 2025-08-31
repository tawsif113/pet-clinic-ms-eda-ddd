package com.bracits.customerquery.application.dto;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OwnerResponse{
    private Long id;
    private String name;
    private String email;

    public OwnerResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
