package com.edapoc.appointmentcommand.presentation.eventconsumer;


import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.GATEWAY_EVENTS_QUEUE;

import com.edapoc.appointmentcommand.application.command.CreateAppointmentCommand;
import com.edapoc.appointmentcommand.presentation.dto.GatewayCreateAppointmentEvent;
import com.edapoc.appointmentcommand.application.service.AppointmentCommandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GatewayEventConsumer {

  private final AppointmentCommandService commandGateway;
  private final ObjectMapper objectMapper;

  @RabbitListener(queues = GATEWAY_EVENTS_QUEUE)
  public void consumeEvent(String message) {
    System.out.println("Received event from gateway: " + message);
    try {
      GatewayCreateAppointmentEvent gatewayEvent = objectMapper.readValue(message, GatewayCreateAppointmentEvent.class);

      CreateAppointmentCommand command = new CreateAppointmentCommand(
          gatewayEvent.traceId(),
          gatewayEvent.petId(),
          gatewayEvent.appointmentDateTime()
      );

      commandGateway.send(command);
    } catch (JsonProcessingException e) {
      System.err.println("Error processing JSON event: " + e.getMessage());
    }
  }
}
