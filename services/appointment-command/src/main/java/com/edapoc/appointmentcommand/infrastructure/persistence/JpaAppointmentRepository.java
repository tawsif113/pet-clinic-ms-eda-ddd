package com.edapoc.appointmentcommand.infrastructure.persistence;

import com.edapoc.appointmentcommand.domain.aggregate.Appointment;
import com.edapoc.appointmentcommand.domain.aggregate.AppointmentRepository;
import com.edapoc.appointmentcommand.domain.valueobject.AppointmentId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaAppointmentRepository implements AppointmentRepository {

  private final AppointmentMapper mapper;
  private final SpringDataAppointmentRepository repository;

  @Override
  public Optional<Appointment> findById(AppointmentId appointmentId) {
    return repository.findById(appointmentId.value()).map(mapper::toDomain);
  }

  @Override
  public void save(Appointment appointment) {
    AppointmentEntity entity = mapper.toEntity(appointment);
    repository.save(entity);
  }
}
