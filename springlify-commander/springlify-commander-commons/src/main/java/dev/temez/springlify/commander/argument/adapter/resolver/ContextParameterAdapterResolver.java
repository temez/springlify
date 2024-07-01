package dev.temez.springlify.commander.argument.adapter.resolver;

import dev.temez.springlify.commander.annotation.context.External;
import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.exception.argument.ArgumentAdapterException;
import dev.temez.springlify.commander.exception.argument.ArgumentAdapterNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * A parameter adapter resolver implementation that resolves parameter adapters based on the application context.
 * <p>
 * This resolver uses the Spring application context to locate parameter adapters for a given parameter type.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContextParameterAdapterResolver implements ParameterAdapterResolver {

  /**
   * The Spring application context used to locate parameter adapters.
   */
  @NotNull
  ApplicationContext applicationContext;

  /**
   * Retrieves the parameter adapter for the specified parameter type using the Spring application context.
   *
   * @param argumentType The class representing the type of parameter for which an adapter is needed.
   * @param <T>          The type of the parameter.
   * @return The parameter adapter for the specified parameter type.
   * @throws ArgumentAdapterNotFoundException If no adapter is found for the specified type.
   * @throws ArgumentAdapterException         If multiple adapters are found for the specified type.
   */
  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T> ParameterAdapter<T> getAdapter(@NotNull Class<T> argumentType) throws ArgumentAdapterNotFoundException, ArgumentAdapterException {
    ResolvableType type = ResolvableType.forClassWithGenerics(ParameterAdapter.class, argumentType);
    String[] beanNamesForType = applicationContext.getBeanNamesForType(type);
    List<ParameterAdapter<T>> argumentAdapters = Arrays.stream(beanNamesForType)
        .map(name -> (ParameterAdapter<T>) applicationContext.getBean(name))
        .filter(adapter -> !adapter.getClass().isAnnotationPresent(External.class))
        .toList();
    if (argumentAdapters.isEmpty()) {
      throw new ArgumentAdapterNotFoundException("No argument adapter found for type %s".formatted(argumentType.getName()));
    }
    if (argumentAdapters.size() > 1) {
      throw new ArgumentAdapterException("Multiple argument adapters found for type %s".formatted(argumentType.getName()));
    }
    return (ParameterAdapter<T>) applicationContext.getBean(beanNamesForType[0]);
  }
}
