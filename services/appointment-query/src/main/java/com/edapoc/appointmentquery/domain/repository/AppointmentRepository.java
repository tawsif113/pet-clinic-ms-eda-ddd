package com.edapoc.appointmentquery.domain.repository;

import com.edapoc.appointmentquery.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
