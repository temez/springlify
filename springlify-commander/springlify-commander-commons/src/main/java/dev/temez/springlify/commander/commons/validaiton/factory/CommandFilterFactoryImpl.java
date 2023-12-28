package dev.temez.springlify.commander.commons.validaiton.factory;

import dev.temez.springlify.commander.commons.annotation.validation.ApplyFilters;
import dev.temez.springlify.commander.commons.annotation.validation.ValidateWith;
import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link CommandFilterFactory} for discovering filter
 * annotations and simple command filters.
 *
 * @since 0.5.8.9dev
 */
@Log4j2
@Component
@RequiredArgsConstructor
public final class CommandFilterFactoryImpl implements CommandFilterFactory {

  @NotNull
  ApplicationContext applicationContext;

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull List<Annotation> getFilterAnnotations(@NotNull Method commandMethod) {
    List<Annotation> annotations = Arrays.stream(commandMethod.getAnnotations())
        .filter(annotation -> annotation.annotationType().isAnnotationPresent(ValidateWith.class))
        .sorted(Comparator.comparingInt(
            annotation -> Optional
                .ofNullable(annotation.annotationType().getAnnotation(Order.class))
                .map(Order::value)
                .orElse(0)
        ))
        .collect(Collectors.toList());

    log.info(
        "Successfully discovered {} filters for {}",
        annotations.size(),
        commandMethod.getName()
    );
    return annotations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull @Unmodifiable List<? extends SimpleCommandFilter> getSimpleFilters(
      @NotNull Method commandMethod) {
    ApplyFilters applyFiltersAnnotation = commandMethod.getAnnotation(ApplyFilters.class);
    if (applyFiltersAnnotation == null) {
      return Collections.emptyList();
    }
    return Arrays.stream(applyFiltersAnnotation.value())
        .map(applicationContext::getBean)
        .toList();
  }
}
