package com.bracits.gateway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
public class Appointment {
  @Id
  private Long id;
  private Long petId;
  private LocalDateTime appointmentDateTime;
}
