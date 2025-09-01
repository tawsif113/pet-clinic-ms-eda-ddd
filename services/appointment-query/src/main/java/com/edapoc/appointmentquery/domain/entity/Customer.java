package com.edapoc.appointmentquery.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers_read")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  private Long petId;
  private Long ownerId;
}

