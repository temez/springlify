package dev.temez.springlify.commander.command.executor.provider.impl;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.argument.adapter.resolver.ParameterAdapterResolver;
import dev.temez.springlify.commander.command.executor.provider.ParameterProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

/**
 * A generic parameter provider implementation that resolves parameter adapters for parsing command parameters.
 * <p>
 * This provider retrieves an appropriate parameter adapter from the resolver based on the parameter's type,
 * then uses the adapter to parse the raw argument into the parameter type.
 * </p>
 *
 * @see ParameterProvider
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Getter
@Order(0)
@Component
@RequiredArgsConstructor
public final class GenericParameterProvider implements ParameterProvider {

  /**
   * The resolver for parameter adapters.
   */
  @NotNull
  ParameterAdapterResolver parameterAdapterResolver;

  /**
   * Initializes the provider.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing GenericArgumentAdapterProvider with order 0");
  }

  /**
   * Parses the raw argument into the appropriate parameter type using a parameter adapter.
   *
   * @param sender      The sender of the command.
   * @param rawArgument The raw argument to be parsed.
   * @param parameter   The method parameter definition.
   * @return The parsed parameter object.
   */
  @Override
  public @NotNull Object parse(@NotNull Sender<?> sender, @NotNull String rawArgument, @NotNull Parameter parameter) {
    ParameterAdapter<?> adapter = parameterAdapterResolver.getAdapter(parameter.getType());
    return adapter.parse(sender, rawArgument);
  }

  /**
   * Determines whether the provider supports parsing for any parameter.
   *
   * @param parameter The method parameter definition.
   * @return {@code true} since this provider supports parsing for any parameter.
   */
  @Override
  public boolean supports(@NotNull Parameter parameter) {
    return true;
  }
}
