package com.bracits.sharedevent.dto;

public class OwnerResponseDto {
    private Long id;
    private String name;
    private String email;

    public OwnerResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
