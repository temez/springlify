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

/**
 * Resolves command filters based on the application context.
 *
 * @see CommandFilter
 * @see ValidateWith
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContextFilterResolver implements CommandFilterResolver {

  /**
   * The application context.
   */
  @NotNull
  ApplicationContext applicationContext;

  /**
   * Retrieves a command filter based on its annotation class.
   *
   * @param <A>        The type of the annotation.
   * @param annotation The annotation class.
   * @return The command filter corresponding to the annotation class.
   * @throws CommandFilterNotFoundException If no command filter is found for the given annotation class.
   */
  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull Class<A> annotation) throws CommandFilterNotFoundException {
    ValidateWith validateWith = annotation.getAnnotation(ValidateWith.class);
    if (validateWith == null) {
      throw new CommandFilterNotFoundException(String.format("No @ValidateWith annotation found on %s", annotation.getSimpleName()));
    }
    try {
      return (CommandFilter<A>) applicationContext.getBean(validateWith.value());
    } catch (NoSuchBeanDefinitionException e) {
      throw new CommandFilterNotFoundException(String.format("No filter implementations for %s found in context", annotation.getSimpleName()));
    }
  }

  /**
   * Retrieves a command filter based on its annotation instance.
   *
   * @param <A>        The type of the annotation.
   * @param annotation The annotation instance.
   * @return The command filter corresponding to the annotation instance.
   * @throws CommandFilterNotFoundException If no command filter is found for the given annotation.
   */
  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull A annotation) throws CommandFilterNotFoundException {
    Class<A> annotationType = (Class<A>) annotation.annotationType();
    return getFilter(annotationType);
  }
}

