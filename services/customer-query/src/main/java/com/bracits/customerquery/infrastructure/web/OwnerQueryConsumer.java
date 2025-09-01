package com.bracits.customerquery.infrastructure.web;

import com.bracits.sharedevent.event.infrastructure.messasing.constant.RabbitMQConstants;
import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import com.bracits.customerquery.application.queryhandler.OwnerQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerQueryConsumer {

    private final OwnerQueryHandler ownerQueryHandler;

    @RabbitListener(queues = RabbitMQConstants.OWNER_CREATED_QUERY_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
    public void consume(OwnerCreatedEvent ownerCreatedEvent) {
        ownerQueryHandler.handle(ownerCreatedEvent);
    }

//    @RabbitListener(queues = RabbitMQConstants.PET_CREATED_QUERY_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
//    public void consume(PetCreatedEvent petCreatedEvent){
//        ownerQueryHandler.handle(petCreatedEvent);
//    }

}
