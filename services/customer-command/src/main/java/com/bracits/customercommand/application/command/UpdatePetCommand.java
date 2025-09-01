package com.bracits.customercommand.application.command;

public record UpdatePetCommand(
    Long petId,
    String name,
    String species,
    Long ownerId,
    String commandId,
    String correlationId
) implements Command {

}