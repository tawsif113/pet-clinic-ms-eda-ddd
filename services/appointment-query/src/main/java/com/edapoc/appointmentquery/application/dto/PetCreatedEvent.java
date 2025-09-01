package com.edapoc.appointmentquery.application.dto;

public record PetCreatedEvent(
    Long ownerId,
    Long petId
) {}
