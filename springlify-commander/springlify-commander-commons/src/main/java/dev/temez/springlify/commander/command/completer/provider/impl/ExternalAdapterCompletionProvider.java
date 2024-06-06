package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.annotation.parameter.Adapter;
import dev.temez.springlify.commander.command.completer.provider.MethodParameterCompletionProvider;
import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.command.sender.Sender;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.List;

@Slf4j
@Order(1)
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExternalAdapterCompletionProvider extends MethodParameterCompletionProvider {

  @NotNull
  ApplicationContext applicationContext;

  public ExternalAdapterCompletionProvider(
      @NotNull CommandFilterService commandFilterService,
      @NotNull ApplicationContext applicationContext
  ) {
    super(commandFilterService);
    this.applicationContext = applicationContext;
  }

  @PostConstruct
  private void initialize() {
    log.debug("Initializing ExternalAdapterCompletionProvider with order 1");
  }

  @Override
  protected boolean supports(@NotNull Parameter parameter) {
    return parameter.isAnnotationPresent(Adapter.class);
  }

  @Override
  protected @NotNull @Unmodifiable List<String> complete(@NotNull Parameter parameter, @NotNull Sender<?> sender) {
    Adapter adapter = parameter.getAnnotation(Adapter.class);
    return applicationContext.getBean(adapter.value()).complete(sender);
  }
}
