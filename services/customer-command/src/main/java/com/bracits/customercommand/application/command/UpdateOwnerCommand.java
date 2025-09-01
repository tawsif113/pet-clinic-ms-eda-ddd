package com.bracits.customercommand.application.command;

public record UpdateOwnerCommand(
    Long ownerId,
    String name,
    String email,
    String commandId,
    String correlationId
) implements Command {

}