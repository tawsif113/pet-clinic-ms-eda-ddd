package com.bracits.customercommand.application.commandhandler;

import com.bracits.customercommand.application.command.Command;

public interface CommandHandler<T extends Command> {

  void handle(T command);

  Class<T> getCommandType();
}