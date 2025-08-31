package com.edapoc.appointmentcommand.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface SpringDataAppointmentRepository extends Repository<AppointmentEntity, UUID> {
  Optional<AppointmentEntity> findById(Long value);
  AppointmentEntity save(AppointmentEntity entity);
}


