package dev.temez.springlify.commander.command.filter.impl;


import dev.temez.springlify.commander.annotation.filter.RequirePermission;
import dev.temez.springlify.commander.command.filter.CommandFilter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * A command filter implementation for the {@link RequirePermission} annotation.
 * <p>
 * This filter checks if the sender has the required permission specified in the {@link RequirePermission} annotation.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Component
public final class RequirePermissionFilter implements CommandFilter<RequirePermission> {

  /**
   * Performs the filtering logic based on the {@link RequirePermission} annotation.
   *
   * @param sender     The sender of the command.
   * @param annotation The {@link RequirePermission} annotation specifying the required permission.
   * @throws CommandFilterException If the sender does not have the required permission.
   */
  @Override
  public void doFilter(@NotNull Sender<?> sender, @NotNull RequirePermission annotation) throws CommandFilterException {
    if (!sender.hasPermission(annotation.value())) {
      throw new CommandFilterException("commander.filter.permission.no-permission");
    }
  }
}
