package com.edapoc.appointmentquery.application.dto;

import java.time.LocalDateTime;

public record AppointmentDetailsDto(
    Long appointmentId,
    Long petId,
    Long ownerId,
    LocalDateTime appointmentDateTime,
    String status
) {}

