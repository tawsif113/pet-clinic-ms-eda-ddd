package com.bracits.gateway.consumer;

import com.bracits.sharedevent.event.customer.OwnerCreatedEvent;
import com.bracits.sharedevent.messaging.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class EventReplyListener {

    private final Map<String, CompletableFuture<OwnerCreatedEvent>> pendingReplies = new ConcurrentHashMap<>();

    public void register(String correlationId, CompletableFuture<OwnerCreatedEvent> future) {
        pendingReplies.put(correlationId, future);
    }
    public OwnerCreatedEvent waitForReply(String correlationId, long timeoutMillis) throws Exception {
        CompletableFuture<OwnerCreatedEvent> future = new CompletableFuture<>();
        register(correlationId, future);
        return future.get(timeoutMillis, TimeUnit.MILLISECONDS);
    }

    @RabbitListener(queues = RabbitMQConstants.OWNER_CREATED_COMMAND_QUEUE)
    public void listenOwnerCreated(OwnerCreatedEvent event) {
        CompletableFuture<OwnerCreatedEvent> future = pendingReplies.remove(event.correlationId());
        if (future != null) {
            future.complete(event);
        }
    }
}