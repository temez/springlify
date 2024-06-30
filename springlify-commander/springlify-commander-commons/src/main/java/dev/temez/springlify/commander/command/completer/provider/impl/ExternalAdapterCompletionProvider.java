package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.annotation.parameter.Adapter;
import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
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

/**
 * A completion provider that supports parameters annotated with {@link Adapter}.
 * <p>
 * This provider leverages Spring's {@link ApplicationContext} to resolve the appropriate
 * {@link ParameterAdapter} for the annotated parameters and provides completion suggestions
 * based on the adapter's {@link ParameterAdapter#complete(Sender)} method.
 * </p>
 * <p>
 * This provider is ordered with a priority of 1.
 * </p>
 *
 * @see Adapter
 * @see ParameterAdapter
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Order(1)
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExternalAdapterCompletionProvider extends MethodParameterCompletionProvider {

  @NotNull
  ApplicationContext applicationContext;

  /**
   * Constructs a new {@code ExternalAdapterCompletionProvider} with the specified command filter service and application context.
   *
   * @param commandFilterService The command filter service to use.
   * @param applicationContext   The application context for resolving adapters.
   */
  public ExternalAdapterCompletionProvider(
      @NotNull CommandFilterService commandFilterService,
      @NotNull ApplicationContext applicationContext
  ) {
    super(commandFilterService);
    this.applicationContext = applicationContext;
  }

  /**
   * Initializes the completion provider. This method is called after the bean has been constructed.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing ExternalAdapterCompletionProvider with order 1");
  }

  /**
   * Determines whether this provider supports the given method parameter.
   * <p>
   * This provider supports parameters annotated with {@link Adapter}.
   * </p>
   *
   * @param parameter The method parameter to check.
   * @return {@code true} if this provider supports the given parameter, {@code false} otherwise.
   */
  @Override
  protected boolean supports(@NotNull Parameter parameter) {
    return parameter.isAnnotationPresent(Adapter.class);
  }

  /**
   * Generates a list of completion suggestions for the given method parameter and sender.
   * <p>
   * This method resolves the {@link ParameterAdapter} specified by the {@link Adapter} annotation
   * on the parameter and delegates the completion to the adapter.
   * </p>
   *
   * @param parameter The method parameter for which to generate suggestions.
   * @param sender    The sender of the command invocation.
   * @return An unmodifiable list of completion suggestions.
   */
  @Override
  protected @NotNull @Unmodifiable List<String> complete(@NotNull Parameter parameter, @NotNull Sender<?> sender) {
    Adapter adapter = parameter.getAnnotation(Adapter.class);
    return applicationContext.getBean(adapter.value()).complete(sender);
  }
}
