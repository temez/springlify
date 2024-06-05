package dev.temez.springlify.commander.command.filter.impl;

import dev.temez.springlify.commander.annotation.filter.RequirePermission;
import dev.temez.springlify.commander.command.filter.CommandFilter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.CommandFilterException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class RequirePermissionFilter implements CommandFilter<RequirePermission> {

  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull RequirePermission annotation) throws CommandFilterException {
    if (!sender.hasPermission(annotation.value())) {
      throw new CommandFilterException("commander.filter.permission.no-permission");
    }
  }
}
