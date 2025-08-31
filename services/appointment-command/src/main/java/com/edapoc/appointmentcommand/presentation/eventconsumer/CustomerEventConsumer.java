package com.edapoc.appointmentcommand.presentation.eventconsumer;

import com.bracits.sharedevent.messaging.RabbitMQConstants;
import com.bracits.sharedevent.event.customer.PetCreatedEvent;
import com.edapoc.appointmentcommand.domain.aggregate.CustomerRepository;
import com.edapoc.appointmentcommand.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEventConsumer.class);
    private final CustomerRepository customerRepository;

    @RabbitListener(queues = RabbitMQConstants.PET_CREATED_QUERY_QUEUE)
    public void consumeOwnerCreatedEvent(PetCreatedEvent event) {
        LOGGER.info("Received customer created event: {}", event);
        Customer customer = new Customer(
                event.ownerId(),
                event.petId()
        );
        customerRepository.save(customer);
        LOGGER.info("Saved new customer with ID: {}", customer.getOwnerId());
    }
}
