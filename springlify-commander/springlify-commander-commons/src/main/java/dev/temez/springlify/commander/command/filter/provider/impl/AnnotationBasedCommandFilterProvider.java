package dev.temez.springlify.commander.command.filter.provider.impl;

import dev.temez.springlify.commander.annotation.filter.ValidateWith;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilterProvider;
import dev.temez.springlify.commander.command.filter.resolver.CommandFilterResolver;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

/**
 * An implementation of {@link CommandFilterProvider} that applies filters based on method annotations.
 * <p>
 * This provider resolves filters using a {@link CommandFilterResolver} based on annotations present on the command method.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class AnnotationBasedCommandFilterProvider implements CommandFilterProvider {

  @NotNull
  CommandFilterResolver commandFilterResolver;

  /**
   * Checks if this provider supports the given command by verifying if its associated method is not null.
   *
   * @param command The command to check.
   * @return {@code true} if this provider supports the command, {@code false} otherwise.
   */
  @Override
  public boolean supports(@NotNull Command command) {
    return command.getCommandInvocationMetadata().getCommandMethod() != null;
  }

  /**
   * Initializes the annotation-based filter provider after construction.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing AnnotationBasedCommandFilteringProvider");
  }

  /**
   * Filters the given command based on method annotations.
   * <p>
   * This method resolves filters using a {@link CommandFilterResolver} based on annotations present on the command method.
   * </p>
   *
   * @param sender  The sender of the command.
   * @param command The command to filter.
   * @throws CommandFilterException If an error occurs while applying the filters.
   */
  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    Method commandMethod = command.getCommandInvocationMetadata().getCommandMethod();
    Arrays.stream(commandMethod.getAnnotations())
        .filter(annotation -> annotation.annotationType().isAnnotationPresent(ValidateWith.class))
        .sorted(Comparator.comparingInt(
            annotation -> Optional
                .ofNullable(annotation.annotationType().getAnnotation(Order.class))
                .map(Order::value)
                .orElse(0)
        ))
        .forEach(annotation -> commandFilterResolver.getFilter(annotation).doFilter(sender, annotation));
  }
}
