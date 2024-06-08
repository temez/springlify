package dev.temez.springlify.commander.command.executor.provider.impl;

import dev.temez.springlify.commander.annotation.parameter.Adapter;
import dev.temez.springlify.commander.command.executor.provider.ParameterProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

/**
 * A parameter provider implementation that resolves external parameter adapters for parsing command parameters.
 * <p>
 * This provider retrieves an appropriate external parameter adapter from the application context based on the
 * {@link Adapter} annotation on the parameter, then uses the adapter to parse the raw argument into the parameter type.
 * </p>
 *
 * @see ParameterProvider
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Getter
@Order(2)
@Component
@RequiredArgsConstructor
public final class ExternalParameterProvider implements ParameterProvider {

  /**
   * The application context for retrieving external parameter adapters.
   */
  @NotNull
  ApplicationContext applicationContext;

  /**
   * Initializes the provider.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing ExternalArgumentAdapterProvider with order 2");
  }

  /**
   * Parses the raw argument into the appropriate parameter type using an external parameter adapter.
   *
   * @param sender      The sender of the command.
   * @param rawArgument The raw argument to be parsed.
   * @param parameter   The method parameter definition.
   * @return The parsed parameter object.
   */
  @Override
  public @NotNull Object parse(@NotNull Sender<?> sender, @NotNull String rawArgument, @NotNull Parameter parameter) {
    Adapter adapterAnnotation = parameter.getAnnotation(Adapter.class);
    return applicationContext.getBean(adapterAnnotation.value()).parse(sender, rawArgument);
  }

  /**
   * Determines whether the provider supports parsing for a parameter annotated with {@link Adapter}.
   *
   * @param parameter The method parameter definition.
   * @return {@code true} if the parameter is annotated with {@link Adapter}, indicating support for external parameter adapters.
   */
  @Override
  public boolean supports(@NotNull Parameter parameter) {
    return parameter.isAnnotationPresent(Adapter.class);
  }
}
