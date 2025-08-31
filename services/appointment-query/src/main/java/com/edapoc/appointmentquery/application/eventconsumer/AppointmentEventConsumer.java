package com.edapoc.appointmentquery.application.eventconsumer;

import com.edapoc.appointmentquery.application.dto.AppointmentCreatedEvent;
import com.edapoc.appointmentquery.domain.entity.Appointment;
import com.edapoc.appointmentquery.domain.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentEventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentEventConsumer.class);
    private final AppointmentRepository appointmentRepository;

    @RabbitListener(queues = "appointment.created.query.queue")
    public void consumeAppointmentCreatedEvent(AppointmentCreatedEvent event) {
        LOGGER.info("Received appointment created event: {}", event);
        Appointment appointment = new Appointment(
                event.appointmentId(),
                event.petId(),
                event.appointmentDateTime()
        );
        appointmentRepository.save(appointment);
        LOGGER.info("Saved new appointment with ID: {}", appointment.getId());
    }
}
