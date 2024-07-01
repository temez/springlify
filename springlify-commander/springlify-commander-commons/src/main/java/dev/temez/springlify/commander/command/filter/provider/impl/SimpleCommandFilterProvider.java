package dev.temez.springlify.commander.command.filter.provider.impl;

import dev.temez.springlify.commander.annotation.filter.ApplyFilters;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilterProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * A simple implementation of {@link CommandFilterProvider} that applies filters specified by the {@link ApplyFilters} annotation.
 * <p>
 * This provider retrieves filters from the application context based on annotations present on the command method.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SimpleCommandFilterProvider implements CommandFilterProvider {

  @NotNull
  ApplicationContext applicationContext;

  /**
   * Checks if this provider supports the given command by inspecting annotations on its associated method.
   *
   * @param command The command to check.
   * @return {@code true} if this provider supports the command, {@code false} otherwise.
   */
  @Override
  public boolean supports(@NotNull Command command) {
    Method commandMethod = command.getCommandInvocationMetadata().getCommandMethod();
    return commandMethod != null && commandMethod.isAnnotationPresent(ApplyFilters.class);
  }

  /**
   * Initializes the filter provider after construction.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing SimpleCommandFilteringProvider");
  }

  /**
   * Applies filters to the given command based on annotations present on its associated method.
   * <p>
   * This method retrieves filters from the application context and applies them to the command.
   * </p>
   *
   * @param sender  The sender of the command.
   * @param command The command to filter.
   * @throws CommandFilterException If an error occurs while applying the filters.
   */
  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    Method commandMethod = command.getCommandInvocationMetadata().getCommandMethod();
    ApplyFilters applyFilters = commandMethod.getAnnotation(ApplyFilters.class);
    Arrays.stream(applyFilters.value())
        .map(applicationContext::getBean)
        .forEach(filter -> filter.doFilter(sender, command));
  }
}
