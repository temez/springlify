package dev.temez.springlify.commander.command.executor.provider.impl;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.argument.adapter.resolver.ArgumentAdapterResolver;
import dev.temez.springlify.commander.command.executor.provider.ArgumentProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

@Slf4j
@Getter
@Order(0)
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class GenericArgumentProvider implements ArgumentProvider {

  @NotNull
  ArgumentAdapterResolver argumentAdapterResolver;

  @PostConstruct
  private void initialize() {
    log.debug("Initializing GenericArgumentAdapterProvider with order 0");
  }


  @Override
  public @NotNull Object parse(@NotNull Sender<?> sender, @NotNull String rawArgument, @NotNull Parameter parameter) {
    ArgumentAdapter<?> adapter = argumentAdapterResolver.getAdapter(parameter.getType());
    return adapter.parse(sender, rawArgument);
  }

  @Override
  public boolean supports(@NotNull Parameter parameter) {
    return true;
  }
}
