package com.edapoc.appointmentcommand.domain.valueobject;

import java.time.LocalDateTime;

public record DateTime(LocalDateTime value) {

  public static DateTime now() {
    return new DateTime(LocalDateTime.now());
  }

  public boolean isBefore(DateTime other) {
    return this.value.isBefore(other.value());
  }
}
