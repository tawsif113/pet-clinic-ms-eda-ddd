package com.bracits.customercommand.application.command;

public record CreatePetCommand(
    String name,
    String species,
    String commandId,
    Long ownerId,
    String correlationId
) {}
