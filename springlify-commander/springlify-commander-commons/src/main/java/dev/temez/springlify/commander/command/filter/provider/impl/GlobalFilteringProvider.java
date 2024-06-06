package dev.temez.springlify.commander.command.filter.provider.impl;

import dev.temez.springlify.commander.annotation.context.Global;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilteringProvider;
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

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class GlobalFilteringProvider implements CommandFilteringProvider {

  @NotNull
  List<SimpleCommandFilter> filters;

  @Override
  public boolean supports(@NotNull Command command) {
    return true;
  }

  @PostConstruct
  private void initialize() {
    log.debug("Initializing GlobalFilteringProvider");
  }

  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    filters.stream()
        .filter(filter -> filter.getClass().isAnnotationPresent(Global.class))
        .forEach(filter -> filter.doFilter(sender, command));
  }
}
