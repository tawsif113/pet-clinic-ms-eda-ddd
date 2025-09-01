package com.edapoc.appointmentquery.infrastructure.messasing.eventconsumer;

import static com.edapoc.appointmentquery.infrastructure.messasing.config.RabbitMQConfig.APPOINTMENT_CREATED_QUEUE;

import com.edapoc.appointmentquery.application.dto.AppointmentCreatedEvent;
import com.edapoc.appointmentquery.domain.entity.Appointment;
import com.edapoc.appointmentquery.domain.entity.Customer;
import com.edapoc.appointmentquery.domain.repository.AppointmentRepository;
import com.edapoc.appointmentquery.domain.repository.CustomerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentEventConsumer {

  private final AppointmentRepository appointmentRepository;
  private final CustomerRepository customerRepository;

  @RabbitListener(queues = APPOINTMENT_CREATED_QUEUE)
  public void consumeAppointmentCreatedEvent(AppointmentCreatedEvent event) {
    log.info("Received AppointmentCreatedEvent: {}", event);

    try {
      Optional<Customer> customerOpt = customerRepository.findById(event.petId());
      if (customerOpt.isEmpty()) {
        log.warn("Customer data not yet available for petId: {}. Will process appointment without ownerId.", event.petId());
      }
      Long ownerId = customerOpt.map(Customer::getOwnerId).orElse(null);

      Appointment appointment = new Appointment(
          event.appointmentId(),
          event.petId(),
          ownerId,
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
