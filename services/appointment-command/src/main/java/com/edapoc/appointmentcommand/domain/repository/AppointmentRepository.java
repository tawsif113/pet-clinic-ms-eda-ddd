package com.edapoc.appointmentcommand.domain.repository;

import com.edapoc.appointmentcommand.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
