package com.edapoc.appointmentcommand.application.commandhandler;

import com.edapoc.appointmentcommand.application.command.CreateAppointmentCommand;
import com.edapoc.appointmentcommand.application.eventproducer.AppointmentEventProducer;
import com.edapoc.appointmentcommand.domain.model.Appointment;
import com.edapoc.appointmentcommand.domain.repository.AppointmentRepository;
import com.edapoc.appointmentcommand.domain.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAppointmentCommandHandler implements CommandHandler<CreateAppointmentCommand> {

  private final AppointmentRepository appointmentRepository;
  private final AppointmentEventProducer eventProducer;
  private final CustomerRepository customerRepository;

  @Transactional
  @Override
  public void handle(CreateAppointmentCommand command) {
    var customer = customerRepository.findById(command.petId());

    if (customer.isEmpty()) {
      eventProducer.sendFeedbackEvent(command.correlationId(), "CreateAppointmentCommand",
          "FAILURE", "Customer not found", null);
      return;
    }

    var appointment = Appointment.create(command.petId(), command.appointmentDateTime());
    appointmentRepository.save(appointment);
    System.out.println("Appointment created with ID: " + appointment.getId());

    eventProducer.sendAppointmentCreatedEvent(appointment.appointmentCreatedEvent());
    eventProducer.sendFeedbackEvent(command.correlationId(), "CreateAppointmentCommand", "SUCCESS",
        "Appointment created successfully", appointment.getId());
  }

  @Override
  public Class<CreateAppointmentCommand> getCommandType() {
    return CreateAppointmentCommand.class;
  }
}

