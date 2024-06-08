package dev.temez.springlify.commander.command.completer;

import dev.temez.springlify.commander.command.completer.provider.CompletionProvider;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * A command completer that uses a list of {@link CompletionProvider} instances to generate
 * completion suggestions for commands.
 * <p>
 * This class is annotated with {@link Component} to be managed by Spring and with
 * {@link RequiredArgsConstructor} and {@link FieldDefaults} for automatic injection and
 * field encapsulation.
 * </p>
 *
 * @see CommandCompleter
 * @see CompletionProvider
 * @see CommandInvocation
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ProviderCommandCompleter implements CommandCompleter {

  @NotNull
  List<CompletionProvider> providers;

  /**
   * Generates a list of completion suggestions for the given command invocation by filtering and
   * invoking the appropriate {@link CompletionProvider} instances.
   *
   * @param commandInvocation The current state of the command invocation.
   * @return An unmodifiable list of completion suggestions.
   */
  @Override
  public @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation) {
    return providers.stream()
        .filter(completionProvider -> completionProvider.supports(commandInvocation))
        .sorted(Comparator.comparingInt(completionProvider -> Optional
            .ofNullable(completionProvider.getClass().getAnnotation(Order.class))
            .map(Order::value)
            .orElse(0)
        ))
        .limit(2)
        .map(completionProvider -> completionProvider.complete(commandInvocation))
        .flatMap(List::stream)
        .toList();
  }

  /**
   * Generates a sorted list of completion suggestions for the given command invocation by filtering
   * and invoking the appropriate {@link CompletionProvider} instances and sorting the results.
   *
   * @param commandInvocation The current state of the command invocation.
   * @return An unmodifiable and sorted list of completion suggestions.
   */
  @Override
  public @NotNull @Unmodifiable List<String> completeSorted(@NotNull CommandInvocation commandInvocation) {
    List<String> completions = new ArrayList<>(complete(commandInvocation));
    if (!commandInvocation.getArguments().isEmpty()) {
      completions.removeIf(completion -> !completion.toLowerCase().startsWith(commandInvocation.getLastArgument().toLowerCase()));
    }
    Collections.sort(completions);
    return Collections.unmodifiableList(completions);
  }
}
