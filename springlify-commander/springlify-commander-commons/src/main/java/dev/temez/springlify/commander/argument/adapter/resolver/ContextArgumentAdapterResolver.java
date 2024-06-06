package dev.temez.springlify.commander.argument.adapter.resolver;

import dev.temez.springlify.commander.annotation.context.External;
import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
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

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ContextArgumentAdapterResolver implements ArgumentAdapterResolver {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T> ArgumentAdapter<T> getAdapter(@NotNull Class<T> argumentType) throws ArgumentAdapterNotFoundException {
    ResolvableType type = ResolvableType.forClassWithGenerics(ArgumentAdapter.class, argumentType);
    String[] beanNamesForType = applicationContext.getBeanNamesForType(type);
    List<ArgumentAdapter<T>> argumentAdapters = Arrays.stream(beanNamesForType)
        .map(name -> (ArgumentAdapter<T>) applicationContext.getBean(name))
        .filter(adapter -> !adapter.getClass().isAnnotationPresent(External.class))
        .toList();
    if (argumentAdapters.isEmpty()) {
      throw new ArgumentAdapterNotFoundException("No argument adapter found for type %s".formatted(argumentType.getName()));
    }
    if (argumentAdapters.size() > 1) {
      throw new ArgumentAdapterException("Multiple argument adapters found for type %s".formatted(argumentType.getName()));
    }
    return (ArgumentAdapter<T>) applicationContext.getBean(beanNamesForType[0]);
  }
}