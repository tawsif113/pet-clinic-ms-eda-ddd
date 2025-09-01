package com.edapoc.appointmentquery.infrastructure.messasing.eventconsumer;

import static com.edapoc.appointmentquery.infrastructure.messasing.config.RabbitMQConfig.APPOINTMENT_CREATED_QUEUE;

import com.edapoc.appointmentquery.application.dto.AppointmentCreatedEvent;
import com.edapoc.appointmentquery.domain.entity.Appointment;
import com.edapoc.appointmentquery.domain.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentEventConsumer {

    private final AppointmentRepository appointmentRepository;

    @RabbitListener(queues = APPOINTMENT_CREATED_QUEUE)
    public void consumeAppointmentCreatedEvent(AppointmentCreatedEvent event) {
      try {
        Appointment appointment = new Appointment(
            event.appointmentId(),
            event.petId(),
            event.appointmentDateTime(),
            "SCHEDULED"
        );
        appointmentRepository.save(appointment);
        log.info("Successfully processed and saved appointment read model for ID: {}", event.appointmentId());
      } catch (Exception e) {
        log.error("Error processing AppointmentCreatedEvent for ID: {}", event.appointmentId(), e);
      }
    }
}
