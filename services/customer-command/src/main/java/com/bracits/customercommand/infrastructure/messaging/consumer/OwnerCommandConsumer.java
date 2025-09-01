package com.bracits.customercommand.infrastructure.messaging.consumer;

import com.bracits.customercommand.application.command.CreateOwnerCommand;
import com.bracits.customercommand.application.command.CreatePetCommand;
import com.bracits.customercommand.application.command.UpdateOwnerCommand;
import com.bracits.customercommand.application.command.UpdatePetCommand;
import com.bracits.customercommand.application.service.CustomerCommandHandlerRegistry;
import com.bracits.sharedevent.event.infrastructure.messasing.constant.RabbitMQConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerCommandConsumer {

  private final CustomerCommandHandlerRegistry customerCommandHandlerRegistry;

  @RabbitListener(queues = RabbitMQConstants.OWNER_CREATE_COMMAND_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
  public void consume(CreateOwnerCommand createOwnerCommand) {
    customerCommandHandlerRegistry.dispatch(createOwnerCommand);
  }

  @RabbitListener(queues = RabbitMQConstants.OWNER_UPDATE_COMMAND_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
  public void consume(UpdateOwnerCommand updateOwnerCommand) {
    customerCommandHandlerRegistry.dispatch(updateOwnerCommand);
  }

  @RabbitListener(queues = RabbitMQConstants.PET_CREATED_COMMAND_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
  public void consume(CreatePetCommand createPetCommand) {
    customerCommandHandlerRegistry.dispatch(createPetCommand);
  }

  @RabbitListener(queues = RabbitMQConstants.PET_UPDATE_COMMAND_QUEUE, containerFactory = "rabbitListenerContainerFactory", ackMode = "AUTO")
  public void consume(UpdatePetCommand updatePetCommand) {
    customerCommandHandlerRegistry.dispatch(updatePetCommand);
  }
}