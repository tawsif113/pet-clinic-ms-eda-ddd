package com.edapoc.appointmentquery.application.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record AppointmentDetailsDto(
    Long appointmentId,
    Long petId,
    Long ownerId,
    LocalDateTime appointmentDateTime,
    String status
) {}

