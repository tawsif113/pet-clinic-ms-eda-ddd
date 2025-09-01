package com.bracits.sharedevent.messaging;

public class RabbitMQConstants {
    public static final String CUSTOMER_EXCHANGE = "customer.exchange";

    public static final String OWNER_CREATE_COMMAND_QUEUE = "owner.create.command.queue";
    public static final String OWNER_CREATED_COMMAND_QUEUE = "owner.created.command.queue";
    public static final String OWNER_UPDATE_COMMAND_QUEUE = "owner.updated.command.queue";
    public static final String OWNER_CREATED_QUERY_QUEUE = "owner.created.query.queue";
    public static final String OWNER_UPDATED_QUERY_QUEUE = "owner.updated.query.queue";

    public static final String OWNER_CREATE_COMMAND_ROUTING_KEY = "owner.create.command.routing.key";
    public static final String OWNER_CREATED_COMMAND_ROUTING_KEY = "owner.created.command.routing.key";
    public static final String OWNER_UPDATE_COMMAND_ROUTING_KEY = "owner.updated.command.routing.key";
    public static final String OWNER_CREATED_QUERY_ROUTING_KEY = "owner.created.query.routing.key";
    public static final String OWNER_UPDATED_QUERY_ROUTING_KEY = "owner.updated.query.routing.key";

    public static final String PET_CREATED_COMMAND_QUEUE = "pet.created.command.queue";
    public static final String PET_CREATED_COMMAND_ROUTING_KEY = "pet.created.command.routing.key";

    public static final String PET_CREATED_QUERY_QUEUE = "pet.created.query.queue";
    public static final String PET_CREATED_QUERY_ROUTING_KEY = "pet.created.routing.key";

    public static final String PET_UPDATE_COMMAND_QUEUE = "pet.updated.command.queue";
    public static final String PET_UPDATE_COMMAND_ROUTING_KEY = "pet.updated.command.routing.key";

    public static final String PET_UPDATED_QUERY_QUEUE = "pet.updated.query.queue";
    public static final String PET_UPDATED_QUERY_ROUTING_KEY = "pet.updated.routing.key";

}
