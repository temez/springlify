package dev.temez.springlify.commander.command.completer;

import dev.temez.springlify.commander.command.completer.provider.CompletionProvider;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ProviderCommandCompleter implements CommandCompleter {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  public @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation) {
    return applicationContext.getBeansOfType(CompletionProvider.class).values()
        .stream()
        .filter(completionProvider -> completionProvider.supports(commandInvocation))
        .sorted(Comparator.comparingInt(
            completionProvider -> Optional
                .ofNullable(completionProvider.getClass().getAnnotation(Order.class))
                .map(Order::value)
                .orElse(0)
        ))
        .limit(2)
        .map(completionProvider -> completionProvider.complete(commandInvocation))
        .flatMap(List::stream)
        .toList();
  }

  @Override
  public @NotNull @Unmodifiable List<String> completeSorted(@NotNull CommandInvocation commandInvocation) {
    List<String> completions = new ArrayList<>(complete(commandInvocation));
    if (!commandInvocation.getArguments().isEmpty()) {
      completions.removeIf(s -> !s.toLowerCase().startsWith(commandInvocation.getLastArgument().toLowerCase()));
    }
    Collections.sort(completions);
    return Collections.unmodifiableList(completions);
  }
}
