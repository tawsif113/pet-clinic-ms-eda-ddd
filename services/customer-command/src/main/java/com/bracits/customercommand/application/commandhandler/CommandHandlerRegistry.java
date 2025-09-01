package com.bracits.customercommand.application.commandhandler;

import com.bracits.customercommand.application.command.Command;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CommandHandlerRegistry {

  private final Map<Class<? extends Command>, CommandHandler<?>> handlers = new HashMap<>();

  public CommandHandlerRegistry(List<CommandHandler<?>> handlerList) {
    handlerList.forEach(handler -> handlers.put(handler.getCommandType(), handler));
  }

  @SuppressWarnings("unchecked")
  public <T extends Command> void dispatch(T command) {
    CommandHandler<T> handler = (CommandHandler<T>) handlers.get(command.getClass());
    if (handler == null) {
      throw new RuntimeException("No handler found for command type: " + command.getClass());
    }
    handler.handle(command);
  }
}