package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.argument.adapter.resolver.ParameterAdapterResolver;
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

/**
 * A completion provider that generically supports all method parameters.
 * <p>
 * This provider uses a {@link ParameterAdapterResolver} to resolve the appropriate {@link ParameterAdapter}
 * for the method parameters and provides completion suggestions based on the adapter's {@link ParameterAdapter#complete(Sender)} method.
 * </p>
 * <p>
 * This provider is ordered with a priority of 0, making it a default provider.
 * </p>
 *
 * @see ParameterAdapterResolver
 * @see ParameterAdapter
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Order(0)
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class GenericCompletionProvider extends MethodParameterCompletionProvider {

  @NotNull
  ParameterAdapterResolver argumentAdapter;

  /**
   * Constructs a new {@code GenericCompletionProvider} with the specified command filter service and parameter adapter resolver.
   *
   * @param commandFilterService The command filter service to use.
   * @param argumentAdapter      The parameter adapter resolver for resolving adapters.
   */
  public GenericCompletionProvider(
      @NotNull CommandFilterService commandFilterService,
      @NotNull ParameterAdapterResolver argumentAdapter
  ) {
    super(commandFilterService);
    this.argumentAdapter = argumentAdapter;
  }

  /**
   * Initializes the completion provider. This method is called after the bean has been constructed.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing GenericCompletionProvider with order 0");
  }

  /**
   * Determines whether this provider supports the given method parameter.
   * <p>
   * This provider generically supports all parameters.
   * </p>
   *
   * @param parameter The method parameter to check.
   * @return {@code true} for all parameters.
   */
  @Override
  protected boolean supports(@NotNull Parameter parameter) {
    return true;
  }

  /**
   * Generates a list of completion suggestions for the given method parameter and sender.
   * <p>
   * This method resolves the {@link ParameterAdapter} for the parameter type using the {@link ParameterAdapterResolver}
   * and delegates the completion to the adapter.
   * </p>
   *
   * @param parameter The method parameter for which to generate suggestions.
   * @param sender    The sender of the command invocation.
   * @return An unmodifiable list of completion suggestions.
   */
  @Override
  protected @NotNull @Unmodifiable List<String> complete(@NotNull Parameter parameter, @NotNull Sender<?> sender) {
    ParameterAdapter<?> adapter = argumentAdapter.getAdapter(parameter.getType());
    return adapter.complete(sender);
  }
}
