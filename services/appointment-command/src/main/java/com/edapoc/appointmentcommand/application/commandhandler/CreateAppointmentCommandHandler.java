package com.edapoc.appointmentcommand.application.commandhandler;

import com.edapoc.appointmentcommand.application.command.CreateAppointmentCommand;
import com.edapoc.appointmentcommand.application.eventproducer.AppointmentEventProducer;
import com.edapoc.appointmentcommand.domain.aggregate.Appointment;
import com.edapoc.appointmentcommand.domain.aggregate.AppointmentRepository;
import com.edapoc.appointmentcommand.domain.aggregate.CustomerRepository;
import com.edapoc.appointmentcommand.domain.valueobject.DateTime;
import com.edapoc.appointmentcommand.domain.valueobject.PetId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAppointmentCommandHandler {

  private final AppointmentRepository appointmentRepository;
  private final AppointmentEventProducer eventProducer;
  private final CustomerRepository customerRepository;

  @Transactional
  public void handle(CreateAppointmentCommand command) {
    var customer = customerRepository.findById(command.petId());

    if (customer.isEmpty()) {
      eventProducer.sendFeedbackEvent(command.traceId(), "CreateAppointmentCommand", "FAILURE", "Customer not found", null);
      return;
    }

    var appointment = Appointment.create(new PetId(command.petId()), new DateTime(command.appointmentDateTime()));
    appointmentRepository.save(appointment);
    System.out.println("Appointment created with ID: " + appointment.getId().value());

    eventProducer.sendAppointmentCreatedEvent(appointment.appointmentCreatedEvent());
    eventProducer.sendFeedbackEvent(command.traceId(), "CreateAppointmentCommand", "SUCCESS", "Appointment created successfully", appointment.getId().value());
  }
}

