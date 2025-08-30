package com.edapoc.appointmentcommand.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String APPOINTMENT_EXCHANGE = "appointment.exchange";
  public static final String FEEDBACK_EXCHANGE = "feedback.exchange";

  public static final String APPOINTMENT_CREATED_QUEUE = "appointment.created.queue";
  public static final String GATEWAY_EVENTS_QUEUE = "gateway.appointment.events";

  public static final String APPOINTMENT_CREATED_ROUTING_KEY = "appointment.created";
  public static final String FEEDBACK_ROUTING_KEY = "feedback.event";

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  @Bean
  public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter);
    return rabbitTemplate;
  }

  @Bean
  public TopicExchange appointmentExchange() {
    return new TopicExchange(APPOINTMENT_EXCHANGE);
  }

  @Bean
  public TopicExchange feedbackExchange() {
    return new TopicExchange(FEEDBACK_EXCHANGE);
  }

  @Bean
  public Queue appointmentCreatedQueue() {
    return new Queue(APPOINTMENT_CREATED_QUEUE, true);
  }

  @Bean
  public Queue gatewayEventsQueue() {
    return new Queue(GATEWAY_EVENTS_QUEUE, true);
  }

  @Bean
  public Binding appointmentCreatedBinding(Queue appointmentCreatedQueue, TopicExchange appointmentExchange) {
    return BindingBuilder.bind(appointmentCreatedQueue)
        .to(appointmentExchange)
        .with(APPOINTMENT_CREATED_ROUTING_KEY);
  }

  // Note: A binding for the feedback exchange is not created here, as this service *produces*
  // feedback events but does not consume them. The consuming service (e.g., the API Gateway)
  // would be responsible for declaring its own queue and binding it to the FEEDBACK_EXCHANGE.
}
