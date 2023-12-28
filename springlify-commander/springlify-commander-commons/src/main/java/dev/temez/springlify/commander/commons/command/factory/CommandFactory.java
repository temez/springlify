package dev.temez.springlify.commander.commons.command.factory;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.FormattedException;
import org.jetbrains.annotations.NotNull;

public interface CommandFactory {

  @NotNull RegisteredCommand get(@NotNull Object command) throws FormattedException;
}
