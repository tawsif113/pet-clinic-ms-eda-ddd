package com.edapoc.appointmentquery.infrastructure.messasing.eventconsumer;

import static com.edapoc.appointmentquery.infrastructure.messasing.config.RabbitMQConfig.CUSTOMER_PET_CREATED_QUEUE;

import com.edapoc.appointmentquery.application.dto.PetCreatedEvent;
import com.edapoc.appointmentquery.domain.entity.Appointment;
import com.edapoc.appointmentquery.domain.entity.Customer;
import com.edapoc.appointmentquery.domain.repository.AppointmentRepository;
import com.edapoc.appointmentquery.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerEventConsumer {

  private final CustomerRepository customerRepository;
  private final AppointmentRepository appointmentRepository;

  @RabbitListener(queues = CUSTOMER_PET_CREATED_QUEUE)
  @Transactional
  public void consumePetCreatedEvent(PetCreatedEvent event) {
    log.info("Received PetCreatedEvent: {}", event);
    try {
      Customer customer = new Customer(event.petId(), event.ownerId());
      customerRepository.save(customer);
      log.info("Successfully created/updated customer projection for petId: {}", event.petId());

      List<Appointment> appointmentsToFix = appointmentRepository.findByPetId(event.petId());

      appointmentsToFix.stream()
          .filter(appointment -> appointment.getOwnerId() == null)
          .forEach(appointment -> {
            appointment.setOwnerId(event.ownerId());
            appointmentRepository.save(appointment);
            log.info("Compensating action: Updated ownerId for appointmentId: {}", appointment.getAppointmentId());
          });

    } catch (Exception e) {
      log.error("Error processing PetCreatedEvent for petId: {}", event.petId(), e);
    }
  }
}