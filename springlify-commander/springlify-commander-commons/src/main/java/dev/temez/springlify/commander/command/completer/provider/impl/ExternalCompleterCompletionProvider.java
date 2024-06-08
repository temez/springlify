package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.annotation.parameter.Completer;
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
 * A completion provider that supports parameters annotated with {@link Completer}.
 * <p>
 * This provider leverages Spring's {@link ApplicationContext} to resolve the appropriate
 * {@link ParameterAdapter} for the annotated parameters and provides completion suggestions
 * based on the completer's {@link ParameterAdapter#complete(Sender)} method.
 * </p>
 * <p>
 * This provider is ordered with a priority of 1.
 * </p>
 *
 * @see Completer
 * @see ParameterAdapter
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Order(1)
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExternalCompleterCompletionProvider extends MethodParameterCompletionProvider {

  @NotNull
  ApplicationContext applicationContext;

  /**
   * Constructs a new {@code ExternalCompleterCompletionProvider} with the specified command filter service and application context.
   *
   * @param commandFilterService The command filter service to use.
   * @param applicationContext   The application context for resolving completers.
   */
  public ExternalCompleterCompletionProvider(
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
    log.debug("Initializing ExternalCompleterCompletionProvider with order 1");
  }

  /**
   * Determines whether this provider supports the given method parameter.
   * <p>
   * This provider supports parameters annotated with {@link Completer}.
   * </p>
   *
   * @param parameter The method parameter to check.
   * @return {@code true} if this provider supports the given parameter, {@code false} otherwise.
   */
  @Override
  protected boolean supports(@NotNull Parameter parameter) {
    return parameter.isAnnotationPresent(Completer.class);
  }

  /**
   * Generates a list of completion suggestions for the given method parameter and sender.
   * <p>
   * This method resolves the {@link ParameterAdapter} specified by the {@link Completer} annotation
   * on the parameter and delegates the completion to the completer.
   * </p>
   *
   * @param parameter The method parameter for which to generate suggestions.
   * @param sender    The sender of the command invocation.
   * @return An unmodifiable list of completion suggestions.
   */
  @Override
  protected @NotNull @Unmodifiable List<String> complete(@NotNull Parameter parameter, @NotNull Sender<?> sender) {
    Completer completer = parameter.getAnnotation(Completer.class);
    return applicationContext.getBean(completer.value()).complete(sender);
  }
}
