package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.argument.adapter.resolver.ArgumentAdapterResolver;
import dev.temez.springlify.commander.command.completer.provider.MethodParameterCompletionProvider;
import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.command.sender.Sender;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.List;

@Slf4j
@Order(0)
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class GenericCompletionProvider extends MethodParameterCompletionProvider {

  @NotNull
  ArgumentAdapterResolver argumentAdapter;

  public GenericCompletionProvider(
      @NotNull CommandFilterService commandFilterService,
      @NotNull ArgumentAdapterResolver argumentAdapter
  ) {
    super(commandFilterService);
    this.argumentAdapter = argumentAdapter;
  }

  @PostConstruct
  private void initialize() {
    log.debug("Initializing GenericCompletionProvider with order 0");
  }

  @Override
  protected boolean supports(@NotNull Parameter parameter) {
    return true;
  }

  @Override
  protected @NotNull @Unmodifiable List<String> complete(@NotNull Parameter parameter, @NotNull Sender<?> sender) {
    ArgumentAdapter<?> adapter = argumentAdapter.getAdapter(parameter.getType());
    return adapter.complete(sender);
  }
}
