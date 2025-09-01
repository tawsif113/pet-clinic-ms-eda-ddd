package com.bracits.customercommand.infrastructure.messaging;

import com.bracits.sharedevent.messaging.RabbitMQConstants;
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
    public Queue ownerCreatedCommandQueue() {
        return new Queue(RabbitMQConstants.OWNER_CREATE_COMMAND_QUEUE);
    }

    @Bean
    public Queue ownerUpdatedCommandQueue() {
        return new Queue(RabbitMQConstants.OWNER_UPDATE_COMMAND_QUEUE);
    }

    @Bean
    public Queue ownerCreatedQueryQueue() {
        return new Queue(RabbitMQConstants.OWNER_CREATED_QUERY_QUEUE);
    }

    @Bean
    public Queue ownerUpdatedQueryQueue() {
        return new Queue(RabbitMQConstants.OWNER_UPDATED_QUERY_QUEUE);
    }

    @Bean
    public Queue petCreatedCommandQueue() {
        return new Queue(RabbitMQConstants.PET_CREATED_COMMAND_QUEUE);
    }

    @Bean
    public Queue petCreatedQueryQueue() {
        return new Queue(RabbitMQConstants.PET_CREATED_QUERY_QUEUE);
    }

    @Bean
    public Queue petUpdatedCommandQueue() {
        return new Queue(RabbitMQConstants.PET_UPDATE_COMMAND_QUEUE);
    }

    @Bean
    public Queue petUpdatedQueryQueue() {
        return new Queue(RabbitMQConstants.PET_UPDATED_QUERY_QUEUE);
    }


    @Bean
    public Binding ownerCreatedBinding() {
        return BindingBuilder.bind(ownerCreatedCommandQueue())
                .to(customerExchange())
                .with(RabbitMQConstants.OWNER_CREATE_COMMAND_ROUTING_KEY);
    }

    @Bean
    public Binding ownerUpdatedBinding() {
        return BindingBuilder.bind(ownerUpdatedCommandQueue())
                .to(customerExchange())
                .with(RabbitMQConstants.OWNER_UPDATE_COMMAND_ROUTING_KEY);
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

    @Bean
    public Binding petCreatedBinding() {
        return BindingBuilder.bind(petCreatedCommandQueue())
                .to(customerExchange())
                .with(RabbitMQConstants.PET_CREATED_COMMAND_ROUTING_KEY);
    }

    @Bean
    public Binding petCreatedQueryBinding() {
        return BindingBuilder.bind(petCreatedQueryQueue())
                .to(customerExchange())
                .with(RabbitMQConstants.PET_CREATED_QUERY_ROUTING_KEY);
    }

    @Bean
    public Binding petUpdatedBinding() {
        return BindingBuilder.bind(petUpdatedCommandQueue())
                .to(customerExchange())
                .with(RabbitMQConstants.PET_UPDATE_COMMAND_ROUTING_KEY);

    }

    @Bean
    public Binding petUpdatedQueryBinding() {
        return BindingBuilder.bind(petUpdatedQueryQueue())
                .to(customerExchange())
                .with(RabbitMQConstants.PET_UPDATED_QUERY_ROUTING_KEY);
    }

}