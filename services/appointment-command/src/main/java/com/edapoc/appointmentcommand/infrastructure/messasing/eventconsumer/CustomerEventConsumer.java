package com.edapoc.appointmentcommand.infrastructure.messasing.eventconsumer;

<<<<<<< Updated upstream:services/appointment-command/src/main/java/com/edapoc/appointmentcommand/presentation/eventconsumer/CustomerEventConsumer.java
import com.bracits.sharedevent.messaging.RabbitMQConstants;
=======
import com.bracits.sharedevent.event.infrastructure.messasing.constant.RabbitMQConstants;
>>>>>>> Stashed changes:services/appointment-command/src/main/java/com/edapoc/appointmentcommand/infrastructure/messasing/eventconsumer/CustomerEventConsumer.java
import com.bracits.sharedevent.event.customer.PetCreatedEvent;
import com.edapoc.appointmentcommand.domain.repository.CustomerRepository;
import com.edapoc.appointmentcommand.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventConsumer {

    private final CustomerRepository customerRepository;

    @RabbitListener(queues = RabbitMQConstants.PET_CREATED_QUERY_QUEUE)
    public void consumeOwnerCreatedEvent(PetCreatedEvent event) {
        Customer customer = new Customer(
                event.ownerId(),
                event.petId()
        );
        customerRepository.save(customer);
    }
}
