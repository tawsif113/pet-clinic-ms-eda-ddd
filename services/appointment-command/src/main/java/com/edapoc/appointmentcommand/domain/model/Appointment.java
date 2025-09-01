package com.edapoc.appointmentcommand.domain.model;

import com.edapoc.appointmentcommand.domain.event.AppointmentCreatedEvent;
import com.edapoc.appointmentcommand.domain.valueobject.AppointmentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "appointments")
@NoArgsConstructor
public class Appointment {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long petId;
  private LocalDateTime appointmentDateTime;
  private AppointmentStatus appointmentStatus;

  private Appointment(Long id, Long petId, LocalDateTime appointmentDateTime) {
    this.id = id;
    this.petId = petId;
    this.appointmentDateTime = appointmentDateTime;
    this.appointmentStatus = AppointmentStatus.SCHEDULED;
  }

  public static Appointment create(Long petId, LocalDateTime appointmentDateTime) {
    if (appointmentDateTime.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("Appointment time cannot be in the past");
    }
    return new Appointment(null, petId, appointmentDateTime);
  }

  public AppointmentCreatedEvent appointmentCreatedEvent() {
    return new AppointmentCreatedEvent(
        this.id,
        this.petId,
        this.appointmentDateTime
    );
  }

}
