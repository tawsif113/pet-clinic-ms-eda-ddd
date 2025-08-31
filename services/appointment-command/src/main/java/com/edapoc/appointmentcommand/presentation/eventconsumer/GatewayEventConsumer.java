package com.edapoc.appointmentcommand.presentation.eventconsumer;


import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.GATEWAY_EVENTS_QUEUE;

import com.edapoc.appointmentcommand.application.command.CreateAppointmentCommand;
import com.edapoc.appointmentcommand.application.service.AppointmentCommandService;
import com.edapoc.appointmentcommand.presentation.dto.GatewayCreateAppointmentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GatewayEventConsumer {

  private final AppointmentCommandService commandService;

  @RabbitListener(queues = GATEWAY_EVENTS_QUEUE)
  public void consumeEvent(GatewayCreateAppointmentEvent gatewayEvent) {
    System.out.println("Received event from gateway: " + gatewayEvent.toString());

    CreateAppointmentCommand command = new CreateAppointmentCommand(
        gatewayEvent.traceId(),
        gatewayEvent.petId(),
        gatewayEvent.appointmentDateTime()
    );

    commandService.send(command);
  }
}
