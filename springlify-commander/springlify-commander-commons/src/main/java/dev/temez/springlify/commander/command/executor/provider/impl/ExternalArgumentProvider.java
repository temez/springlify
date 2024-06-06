package dev.temez.springlify.commander.command.executor.provider.impl;

import dev.temez.springlify.commander.annotation.parameter.Adapter;
import dev.temez.springlify.commander.command.executor.provider.ArgumentProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

@Slf4j
@Getter
@Order(2)
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExternalArgumentProvider implements ArgumentProvider {

  @NotNull
  ApplicationContext applicationContext;

  @PostConstruct
  private void initialize() {
    log.debug("Initializing ExternalArgumentAdapterProvider with order 2");
  }

  @Override
  public @NotNull Object parse(@NotNull Sender<?> sender, @NotNull String rawArgument, @NotNull Parameter parameter) {
    Adapter adapterAnnotation = parameter.getAnnotation(Adapter.class);
    return applicationContext.getBean(adapterAnnotation.value()).parse(sender, rawArgument);
  }

  @Override
  public boolean supports(@NotNull Parameter parameter) {
    return parameter.isAnnotationPresent(Adapter.class);
  }
}
