package dev.temez.springlify.commander.command.filter.provider.impl;

import dev.temez.springlify.commander.annotation.context.Global;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilterProvider;
import dev.temez.springlify.commander.command.filter.simple.SimpleCommandFilter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A global implementation of {@link CommandFilterProvider} that applies global filters to all commands.
 * <p>
 * This provider applies filters annotated with {@link Global} to all commands regardless of their individual support.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GlobalFilterProvider implements CommandFilterProvider {

  @NotNull
  List<SimpleCommandFilter> filters;

  /**
   * Indicates unconditional support for all commands.
   *
   * @param command The command to check.
   * @return Always returns {@code true} since global filters apply to all commands.
   */
  @Override
  public boolean supports(@NotNull Command command) {
    return true;
  }

  /**
   * Initializes the global filter provider after construction.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing GlobalFilteringProvider");
  }

  /**
   * Applies global filters to the given command.
   * <p>
   * This method applies filters annotated with {@link Global} to all commands.
   * </p>
   *
   * @param sender  The sender of the command.
   * @param command The command to filter.
   * @throws CommandFilterException If an error occurs while applying the global filters.
   */
  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    filters.stream()
        .filter(filter -> filter.getClass().isAnnotationPresent(Global.class))
        .forEach(filter -> filter.doFilter(sender, command));
  }
}
