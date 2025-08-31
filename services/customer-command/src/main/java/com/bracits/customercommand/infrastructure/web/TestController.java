package com.bracits.customercommand.infrastructure.web;

import com.bracits.customercommand.application.command.CreatePetCommand;
import java.util.UUID;

import com.bracits.sharedevent.event.RabbitMQConstants;
import com.bracits.customercommand.application.command.CreateOwnerCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

  private final RabbitTemplate rabbitTemplate;

  public TestController(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @GetMapping
  public ResponseEntity<String> sendTestMessage() {
    return ResponseEntity.ok("Test message sent to ZZZ");
  }

  @PostMapping("/create-owner")
  public ResponseEntity<String> sendCreateOwnerCommand(
      @RequestParam String name,
      @RequestParam String email) {

    CreateOwnerCommand cmd = new CreateOwnerCommand(
        name,
        email,
        UUID.randomUUID().toString(),
        UUID.randomUUID().toString()
    );

    rabbitTemplate.convertAndSend(
        RabbitMQConstants.CUSTOMER_EXCHANGE,
        RabbitMQConstants.OWNER_CREATED_COMMAND_ROUTING_KEY,
        cmd
    );

    return ResponseEntity.ok("CreateOwnerCommand sent to RabbitMQ");
  }

  @PostMapping("/create-pet")
  public ResponseEntity<String> sendCreatePetCommand(
      @RequestParam String name,
      @RequestParam String species,
      @RequestParam Long ownerId) {

    CreatePetCommand cmd = new CreatePetCommand(
        name,
        species,
        UUID.randomUUID().toString(),
        ownerId
    );

    rabbitTemplate.convertAndSend(
        RabbitMQConstants.CUSTOMER_EXCHANGE, RabbitMQConstants.PET_CREATED_COMMAND_ROUTING_KEY,
        cmd);
    return ResponseEntity.ok("CreatePetCommand sent to RabbitMQ");
  }
}
