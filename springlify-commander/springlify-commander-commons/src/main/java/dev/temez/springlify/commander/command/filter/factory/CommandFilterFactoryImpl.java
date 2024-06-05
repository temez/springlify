package dev.temez.springlify.commander.command.filter.factory;

import dev.temez.springlify.commander.command.filter.CommandFilter;
import dev.temez.springlify.commander.exception.CommandFilterException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandFilterFactoryImpl implements CommandFilterFactory {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T extends Annotation> CommandFilter<T> getFilter(@NotNull Class<T> filterAnnotation) throws CommandFilterException {
    ResolvableType type = ResolvableType.forClassWithGenerics(CommandFilter.class, filterAnnotation);
    String[] beanNamesForType = applicationContext.getBeanNamesForType(type);
    if (beanNamesForType.length == 0) {
      throw new CommandFilterException("No filter implementation found for annotation %s".formatted(filterAnnotation.getSimpleName()));
    }
    if (beanNamesForType.length > 1) {
      throw new CommandFilterException("Multiple filter implementations found for annotation %s".formatted(filterAnnotation.getSimpleName()));
    }
    return (CommandFilter<T>) applicationContext.getBean(beanNamesForType[0]);

  }
}
