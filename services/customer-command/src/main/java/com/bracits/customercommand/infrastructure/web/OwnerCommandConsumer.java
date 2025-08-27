package com.bracits.customercommand.infrastructure.web;

import com.bracits.commonevent.event.RabbitMQConstants;
import com.bracits.customercommand.application.command.CreateOwnerCommand;
import com.bracits.customercommand.application.command.UpdateOwnerCommand;
import com.bracits.customercommand.application.commandhandler.OwnerCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerCommandConsumer {

  private final OwnerCommandHandler ownerCommandHandler;

  @RabbitListener(queues = RabbitMQConstants.OWNER_CREATED_COMMAND_QUEUE, containerFactory = "rabbitListenerContainerFactory",ackMode = "AUTO")
  public void consume(CreateOwnerCommand createOwnerCommand) {
    ownerCommandHandler.handle(createOwnerCommand);
  }

  @RabbitListener(queues = RabbitMQConstants.OWNER_UPDATED_COMMAND_QUEUE, containerFactory = "rabbitListenerContainerFactory",ackMode = "AUTO")
  public void consume(UpdateOwnerCommand updateOwnerCommand) {
    ownerCommandHandler.handle(updateOwnerCommand);
  }
}