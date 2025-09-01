package com.bracits.sharedevent.dto;

import java.time.LocalDateTime;
import java.util.List;

public interface OwnerReadResponseDto {
    Long getId();
    String getName();
    String getEmail();
    List<PetReadResponseDto> getPets();
    LocalDateTime getLastUpdated();
}