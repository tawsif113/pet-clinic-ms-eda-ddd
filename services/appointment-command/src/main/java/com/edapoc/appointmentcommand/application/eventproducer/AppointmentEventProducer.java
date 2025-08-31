package com.edapoc.appointmentcommand.application.eventproducer;

import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.APPOINTMENT_CREATED_ROUTING_KEY;
import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.APPOINTMENT_EXCHANGE;
import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.FEEDBACK_EXCHANGE;
import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.FEEDBACK_ROUTING_KEY;

import com.edapoc.appointmentcommand.domain.event.AppointmentCreatedEvent;
import com.edapoc.appointmentcommand.domain.event.CommandFeedbackEvent;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppointmentEventProducer {

  private final RabbitTemplate rabbitTemplate;

  public AppointmentEventProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendAppointmentCreatedEvent(AppointmentCreatedEvent event) {
    System.out.println("Sending appointment created event: " + event);
    rabbitTemplate.convertAndSend(APPOINTMENT_EXCHANGE, APPOINTMENT_CREATED_ROUTING_KEY, event);
  }

  public void sendFeedbackEvent(String traceId, String commandType, String status, String message, Long appointmentId) {
    CommandFeedbackEvent feedbackEvent = new CommandFeedbackEvent(traceId, commandType, status, message, appointmentId);
    System.out.println("Sending feedback event: " + feedbackEvent);
    rabbitTemplate.convertAndSend(FEEDBACK_EXCHANGE, FEEDBACK_ROUTING_KEY, feedbackEvent);
  }

}
