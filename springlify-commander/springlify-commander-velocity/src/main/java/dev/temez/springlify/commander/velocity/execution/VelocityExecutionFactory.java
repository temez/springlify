package dev.temez.springlify.commander.velocity.execution;

import com.velocitypowered.api.command.SimpleCommand;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.CommandExecutionFactory;
import dev.temez.springlify.commander.velocity.sender.VelocityCommandSender;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class VelocityExecutionFactory implements CommandExecutionFactory {

  @Override
  public @NotNull CommandExecution createExecution(@NotNull RegisteredCommand registeredCommand,
                                                   Object @NotNull ... objects) {
    SimpleCommand.Invocation invocation = (SimpleCommand.Invocation) objects[0];
    return CommandExecution.builder()
        .command(registeredCommand)
        .commandSender(new VelocityCommandSender(invocation.source()))
        .arguments(convertArguments(invocation.arguments()))
        .build();
  }

  @NotNull List<String> convertArguments(String @NotNull [] rawArgs) {
    String invocationString = String.join(":", rawArgs);
    invocationString = invocationString.replace(" ", "");
    return Arrays.stream(invocationString.split(":"))
        .toList();
  }
}
