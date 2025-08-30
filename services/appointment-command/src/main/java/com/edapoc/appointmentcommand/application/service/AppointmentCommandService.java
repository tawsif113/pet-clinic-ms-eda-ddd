package com.edapoc.appointmentcommand.application.service;

import com.edapoc.appointmentcommand.application.command.CreateAppointmentCommand;
import com.edapoc.appointmentcommand.application.commandhandler.CreateAppointmentCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentCommandService {
  private final CreateAppointmentCommandHandler createHandler;

  public void send(CreateAppointmentCommand command) {
    createHandler.handle(command);
  }
}
