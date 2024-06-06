package dev.temez.springlify.commander.command.filter.resolver;

import dev.temez.springlify.commander.annotation.filter.ValidateWith;
import dev.temez.springlify.commander.command.filter.CommandFilter;
import dev.temez.springlify.commander.exception.filter.CommandFilterNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContextFilterResolver implements CommandFilterResolver {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull Class<A> annotation) throws CommandFilterNotFoundException {
    ValidateWith validateWith = annotation.getAnnotation(ValidateWith.class);
    if (validateWith == null) {
      throw new CommandFilterNotFoundException("No @ValidateWith annotation found on %s".formatted(annotation.getSimpleName()));
    }
    try {
      return (CommandFilter<A>) applicationContext.getBean(validateWith.value());
    } catch (NoSuchBeanDefinitionException e) {
      throw new CommandFilterNotFoundException("No filter implementations for %s found in context".formatted(annotation.getSimpleName()));
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull A annotation) throws CommandFilterNotFoundException {
    Class<A> annotationType = (Class<A>) annotation.annotationType();
    return getFilter(annotationType);
  }
}
