package com.edapoc.appointmentcommand.domain.aggregate;

import com.edapoc.appointmentcommand.domain.event.AppointmentCreatedEvent;
import com.edapoc.appointmentcommand.domain.valueobject.AppointmentId;
import com.edapoc.appointmentcommand.domain.valueobject.AppointmentStatus;
import com.edapoc.appointmentcommand.domain.valueobject.DateTime;
import com.edapoc.appointmentcommand.domain.valueobject.PetId;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Appointment {
  private final AppointmentId id;
  private final PetId petId;
  private DateTime appointmentDateTime;
  private AppointmentStatus appointmentStatus;

  private Appointment(AppointmentId id, PetId petId, DateTime appointmentDateTime) {
    this.id = id;
    this.petId = petId;
    this.appointmentDateTime = appointmentDateTime;
    this.appointmentStatus = AppointmentStatus.SCHEDULED;
  }


  public static Appointment create(PetId petId, DateTime appointmentDateTime) {
    if (appointmentDateTime.isBefore(DateTime.now())) {
      throw new IllegalArgumentException("Appointment time cannot be in the past");
    }
    return new Appointment(null, petId, appointmentDateTime);
  }

  public static Appointment fromState(AppointmentId id, PetId petId, DateTime appointmentDateTime, AppointmentStatus status) {
    Appointment appointment = new Appointment(id, petId, appointmentDateTime);
    appointment.appointmentStatus = status;
    return appointment;
  }

  public void reschedule(DateTime newDateTime) {
    if (newDateTime.isBefore(DateTime.now())) {
      throw new IllegalArgumentException("Cannot reschedule to a past date");
    }
    this.appointmentDateTime = newDateTime;
  }
  public void cancel() {
    this.appointmentStatus = AppointmentStatus.CANCELED;
  }

  public AppointmentCreatedEvent appointmentCreatedEvent() {
    return new AppointmentCreatedEvent(
        this.id.value(),
        this.petId.value(),
        this.appointmentDateTime.value()
    );
  }

}
