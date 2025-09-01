package com.edapoc.appointmentquery.application.mapper;

import com.edapoc.appointmentquery.application.dto.AppointmentDetailsDto;
import com.edapoc.appointmentquery.domain.entity.Appointment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {
  public AppointmentDetailsDto toDto(Appointment appointment) {
    if (appointment == null) {
      return null;
    }

    return new AppointmentDetailsDto(
        appointment.getAppointmentId(),
        appointment.getPetId(),
        appointment.getOwnerId(),
        appointment.getAppointmentDateTime(),
        appointment.getStatus()
    );
  }

  public List<AppointmentDetailsDto> toDtoList(List<Appointment> appointments) {
    if (appointments == null || appointments.isEmpty()) {
      return Collections.emptyList();
    }

    return appointments.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
