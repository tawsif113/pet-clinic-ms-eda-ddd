package com.bracits.customerquery.infrastructure.messaging.consumer;

import com.bracits.customerquery.application.service.CreateOwnerEventHandler;
import com.bracits.customerquery.application.service.CreatePetEventHandler;
import com.bracits.customerquery.application.service.UpdateOwnerEventHandler;
import com.bracits.customerquery.application.service.UpdatePetEventHandler;
import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import com.bracits.sharedevent.event.customer.OwnerUpdatedEvent;
import com.bracits.sharedevent.event.customer.PetCreatedEvent;
import com.bracits.sharedevent.event.customer.PetUpdatedEvent;
import com.bracits.sharedevent.messaging.RabbitMQConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerEventConsumer {

    private final UpdatePetEventHandler updatePetEventHandler;
    private final CreateOwnerEventHandler createOwnerEventHandler;
    private final CreatePetEventHandler createPetEventHandler;
    private final UpdateOwnerEventHandler updateOwnerEventHandler;

    @RabbitListener(queues = RabbitMQConstants.OWNER_CREATED_COMMAND_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
    public void consume(OwnerCreatedEvent ownerCreatedEvent) {
        createOwnerEventHandler.handle(ownerCreatedEvent);
    }

    @RabbitListener(queues = RabbitMQConstants.OWNER_UPDATED_QUERY_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
    public void consume(OwnerUpdatedEvent ownerUpdatedEvent) {
        updateOwnerEventHandler.handle(ownerUpdatedEvent);
    }

    @RabbitListener(queues = RabbitMQConstants.PET_CREATED_QUERY_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
    public void consume(PetCreatedEvent petCreatedEvent) {
        createPetEventHandler.handle(petCreatedEvent);
    }

    @RabbitListener(queues = RabbitMQConstants.PET_UPDATED_QUERY_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
    public void consume(PetUpdatedEvent petUpdatedEvent) {
        updatePetEventHandler.handle(petUpdatedEvent);
    }
}
