package com.bracits.customerquery.infrastructure.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
      Jackson2JsonMessageConverter messageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter);
    return rabbitTemplate;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory connectionFactory,
      Jackson2JsonMessageConverter messageConverter) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter);
    return factory;
  }

  @Bean
  public TopicExchange customerExchange() {
    return new TopicExchange(RabbitMQConstants.CUSTOMER_EXCHANGE);
  }

  @Bean
  public Queue ownerCreatedQueryQueue(){
    return new Queue(RabbitMQConstants.OWNER_CREATED_QUERY_QUEUE);
  }
  @Bean
  public Queue ownerUpdatedQueryQueue(){
    return new Queue(RabbitMQConstants.OWNER_UPDATED_QUERY_QUEUE);
  }

  @Bean
  public Binding ownerQueryCreatedBinding() {
    return BindingBuilder.bind(ownerCreatedQueryQueue())
        .to(customerExchange())
        .with(RabbitMQConstants.OWNER_CREATED_QUERY_ROUTING_KEY);
  }
  @Bean
  public Binding ownerQueryUpdatedBinding() {
    return BindingBuilder.bind(ownerUpdatedQueryQueue())
        .to(customerExchange())
        .with(RabbitMQConstants.OWNER_UPDATED_QUERY_ROUTING_KEY);
  }
}