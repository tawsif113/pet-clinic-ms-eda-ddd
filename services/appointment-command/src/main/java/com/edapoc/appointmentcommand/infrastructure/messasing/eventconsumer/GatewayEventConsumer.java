package com.edapoc.appointmentcommand.infrastructure.messasing.eventconsumer;


import static com.edapoc.appointmentcommand.infrastructure.messasing.config.RabbitMQConfig.GATEWAY_EVENTS_QUEUE;

import com.edapoc.appointmentcommand.application.command.CreateAppointmentCommand;
import com.edapoc.appointmentcommand.application.service.AppointmentCommandHandlerRegistry;
import com.edapoc.appointmentcommand.infrastructure.dto.GatewayCreateAppointmentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GatewayEventConsumer {

  private final AppointmentCommandHandlerRegistry appointmentCommandHandlerRegistry;

  @RabbitListener(queues = GATEWAY_EVENTS_QUEUE)
  public void consumeEvent(GatewayCreateAppointmentEvent gatewayEvent) {
    System.out.println("Received event from gateway: " + gatewayEvent.toString());

    CreateAppointmentCommand command = new CreateAppointmentCommand(
        gatewayEvent.traceId(),
        gatewayEvent.petId(),
        gatewayEvent.appointmentDateTime()
    );

    appointmentCommandHandlerRegistry.dispatch(command);
  }
}
