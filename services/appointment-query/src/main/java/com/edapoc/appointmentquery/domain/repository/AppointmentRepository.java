package com.edapoc.appointmentquery.domain.repository;

import com.edapoc.appointmentquery.domain.entity.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findByPetId(Long petId);
}
