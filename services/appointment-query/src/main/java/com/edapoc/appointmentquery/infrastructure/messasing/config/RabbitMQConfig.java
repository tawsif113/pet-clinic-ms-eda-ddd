package com.edapoc.appointmentquery.infrastructure.messasing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  // Exchanges
  public static final String APPOINTMENT_EXCHANGE = "appointment.exchange";
  public static final String CUSTOMER_EXCHANGE = "customer.exchange";

  // Appointment Queues & Routing Keys
  public static final String APPOINTMENT_CREATED_QUEUE = "appointment.created.queue.query";
  public static final String APPOINTMENT_CREATED_ROUTING_KEY = "appointment.created";

  // Customer Queues & Routing Keys
  public static final String CUSTOMER_PET_CREATED_QUEUE = "customer.pet.created.queue.query";
  public static final String CUSTOMER_PET_CREATED_ROUTING_KEY = "customer.pet.created";

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
  public TopicExchange customerExchange() {
    return new TopicExchange(CUSTOMER_EXCHANGE);
  }

  @Bean
  public Queue appointmentCreatedQueue() {
    return new Queue(APPOINTMENT_CREATED_QUEUE, true);
  }

  @Bean
  public Queue petCreatedQueue() {
    return new Queue(CUSTOMER_PET_CREATED_QUEUE, true);
  }

  @Bean
  public Binding appointmentCreatedBinding(Queue appointmentCreatedQueue, TopicExchange appointmentExchange) {
    return BindingBuilder.bind(appointmentCreatedQueue)
        .to(appointmentExchange)
        .with(APPOINTMENT_CREATED_ROUTING_KEY);
  }

  @Bean
  public Binding petCreatedBinding(Queue petCreatedQueue, TopicExchange customerExchange) {
    return BindingBuilder.bind(petCreatedQueue)
        .to(customerExchange)
        .with(CUSTOMER_PET_CREATED_ROUTING_KEY);
  }
}
