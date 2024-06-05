package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.argument.adapter.ArgumentAdapterFactory;
import dev.temez.springlify.commander.command.completer.provider.MethodParameterCompletionProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class GenericCompletionProvider extends MethodParameterCompletionProvider {

  @NotNull
  ArgumentAdapterFactory argumentAdapter;

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
    List<String> result = adapter.complete(sender);
    if (!result.isEmpty()) {
      return result;
    }
    return List.of(String.format("<%s>", parameter.getName()));
  }
}
