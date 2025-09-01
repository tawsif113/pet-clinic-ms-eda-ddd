package com.bracits.customercommand.application.command;

public record CreateOwnerCommand(
    String name,
    String email,
    String commandId,
    String correlationId
) {}
