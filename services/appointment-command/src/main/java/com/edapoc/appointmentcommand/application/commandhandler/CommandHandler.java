package com.edapoc.appointmentcommand.application.commandhandler;


import com.edapoc.appointmentcommand.application.command.Command;

public interface CommandHandler<T extends Command> {

  void handle(T command);

  Class<T> getCommandType();
}