package com.edapoc.customerquery.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public interface OwnerReadModel {
    Long getId();
    String getName();
    String getEmail();
    List<PetReadModel> getPets();
    LocalDateTime getLastUpdated();
}