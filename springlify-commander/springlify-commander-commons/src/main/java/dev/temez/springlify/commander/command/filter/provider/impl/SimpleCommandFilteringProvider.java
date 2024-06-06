package dev.temez.springlify.commander.command.filter.provider.impl;

import dev.temez.springlify.commander.annotation.filter.ApplyFilters;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilteringProvider;
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

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SimpleCommandFilteringProvider implements CommandFilteringProvider {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  public boolean supports(@NotNull Command command) {
    Method commandMethod = command.getCommandInvocationMetadata().getCommandMethod();
    return commandMethod != null && commandMethod.isAnnotationPresent(ApplyFilters.class);
  }

  @PostConstruct
  private void initialize() {
    log.debug("Initializing SimpleCommandFilteringProvider");
  }

  @Override
  @SuppressWarnings("DataFlowIssue")
  public void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    Method commandMethod = command.getCommandInvocationMetadata().getCommandMethod();
    ApplyFilters applyFilters = commandMethod.getAnnotation(ApplyFilters.class);
    Arrays.stream(applyFilters.value())
        .map(applicationContext::getBean)
        .forEach(filter -> filter.doFilter(sender, command));
  }
}
