package com.bracits.customercommand.application.command;

public record CreateOwnerCommand(
    String name,
    String email,
    String commandId,      // unique ID for tracking
    String correlationId   // optional: trace across services
) {}
