
package com.edapoc.appointmentcommand.presentation.rest;

import com.edapoc.appointmentcommand.presentation.dto.GatewayCreateAppointmentEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.APPOINTMENT_EXCHANGE;
import static com.edapoc.appointmentcommand.infrastructure.config.RabbitMQConfig.GATEWAY_APPOINTMENT_CREATE_ROUTING_KEY;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody GatewayCreateAppointmentEvent event) {
        rabbitTemplate.convertAndSend(APPOINTMENT_EXCHANGE, GATEWAY_APPOINTMENT_CREATE_ROUTING_KEY, event);
        return "Message published: " + event.toString();
    }
}
