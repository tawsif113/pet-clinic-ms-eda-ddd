package com.bracits.commonevent.event;

public class RabbitMQConstants {
  public static final String CUSTOMER_EXCHANGE = "customer.exchange";

  public static final String OWNER_CREATED_COMMAND_QUEUE = "owner.created.command.queue";
  public static final String OWNER_UPDATED_COMMAND_QUEUE = "owner.updated.command.queue";

  public static final String OWNER_CREATED_COMMAND_ROUTING_KEY = "owner.created.command.routing.key";
  public static final String OWNER_UPDATED_COMMAND_ROUTING_KEY = "owner.updated.command.routing.key";

  public static final String OWNER_CREATED_QUERY_QUEUE = "owner.created.query.queue";
  public static final String OWNER_UPDATED_QUERY_QUEUE = "owner.updated.query.queue";

  public static final String OWNER_CREATED_QUERY_ROUTING_KEY = "owner.created.routing.key";
  public static final String OWNER_UPDATED_QUERY_ROUTING_KEY = "owner.updated.routing.key";
}
