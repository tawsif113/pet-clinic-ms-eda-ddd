package com.edapoc.appointmentquery.application.dto;

import com.edapoc.appointmentquery.domain.entity.Appointment;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record AppointmentDetailsDto(
    Long appointmentId,
    Long petId,
    LocalDateTime appointmentDateTime,
    String status
) {
  public static AppointmentDetailsDto fromEntity(Appointment appointment) {
    return AppointmentDetailsDto.builder()
        .appointmentId(appointment.getAppointmentId())
        .petId(appointment.getPetId())
        .appointmentDateTime(appointment.getAppointmentDateTime())
        .status(appointment.getStatus())
        .build();
  }
}

