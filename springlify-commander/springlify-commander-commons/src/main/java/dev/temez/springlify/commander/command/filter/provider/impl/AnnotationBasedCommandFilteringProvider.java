package dev.temez.springlify.commander.command.filter.provider.impl;

import dev.temez.springlify.commander.annotation.filter.ValidateWith;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilteringProvider;
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

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class AnnotationBasedCommandFilteringProvider implements CommandFilteringProvider {

  @NotNull
  CommandFilterResolver commandFilterResolver;

  @Override
  public boolean supports(@NotNull Command command) {
    return command.getCommandInvocationMetadata().getCommandMethod() != null;
  }

  @PostConstruct
  private void initialize() {
    log.debug("Initializing AnnotationBasedCommandFilteringProvider");
  }

  @Override
  @SuppressWarnings("DataFlowIssue")
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
