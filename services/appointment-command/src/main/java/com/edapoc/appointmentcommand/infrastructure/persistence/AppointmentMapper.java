package com.edapoc.appointmentcommand.infrastructure.persistence;

import com.edapoc.appointmentcommand.domain.aggregate.Appointment;
import com.edapoc.appointmentcommand.domain.valueobject.AppointmentId;
import com.edapoc.appointmentcommand.domain.valueobject.DateTime;
import com.edapoc.appointmentcommand.domain.valueobject.PetId;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

  public AppointmentEntity toEntity(Appointment appointment) {
    return new AppointmentEntity(
        appointment.getId().value(),
        appointment.getPetId().value(),
        appointment.getAppointmentDateTime().value(),
        appointment.getAppointmentStatus()
    );
  }

  public Appointment toDomain(AppointmentEntity entity) {
    return Appointment.fromState(
        new AppointmentId(entity.getId()),
        new PetId(entity.getPetId()),
        new DateTime(entity.getAppointmentDateTime()),
        entity.getAppointmentStatus()
    );
  }
}