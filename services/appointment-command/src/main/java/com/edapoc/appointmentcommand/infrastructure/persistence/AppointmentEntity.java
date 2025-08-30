package com.edapoc.appointmentcommand.infrastructure.persistence;

import com.edapoc.appointmentcommand.domain.valueobject.AppointmentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
  @Id
  private UUID id;
  private UUID petId;
  private LocalDateTime appointmentDateTime;

  @Enumerated(EnumType.STRING)
  private AppointmentStatus appointmentStatus;
}
