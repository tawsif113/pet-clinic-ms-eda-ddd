package com.edapoc.appointmentcommand.domain.aggregate;

import com.edapoc.appointmentcommand.domain.valueobject.AppointmentId;
import java.util.Optional;

public interface AppointmentRepository {
  Optional<Appointment> findById(AppointmentId appointmentId);
  void save(Appointment appointment);
}
