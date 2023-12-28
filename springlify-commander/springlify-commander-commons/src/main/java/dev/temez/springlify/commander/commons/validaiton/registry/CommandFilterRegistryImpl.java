package dev.temez.springlify.commander.commons.validaiton.registry;

import dev.temez.springlify.commander.commons.validaiton.CommandFilter;
import dev.temez.springlify.platform.commons.exception.FormattedException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link CommandFilterRegistry} for managing and
 * retrieving command filters based on filter annotations.
 *
 * @since 0.5.8.9dev
 */
@Log4j2
@Component
@RequiredArgsConstructor
public final class CommandFilterRegistryImpl implements CommandFilterRegistry {

  @NotNull
  List<CommandFilter<?>> registeredFilters = new ArrayList<>();

  @NotNull
  ApplicationContext applicationContext;

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T extends Annotation> CommandFilter<T> get(
      @NotNull Class<T> filterAnnotation
  ) throws FormattedException {
    return (CommandFilter<T>) registeredFilters
        .stream()
        .filter(filter -> filter.getFilterAnnotationType().equals(filterAnnotation))
        .findFirst()
        .orElseThrow(
            () -> new FormattedException(
                "No filter for %s found in the registry!",
                filterAnnotation.getSimpleName()
            )
        );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void register(@NotNull CommandFilter<?> commandFilter) throws FormattedException {
    if (registeredFilters.contains(commandFilter)) {
      throw new FormattedException(
          "%s already registered in the registry!",
          commandFilter.getClass().getSimpleName()
      );
    }
    registeredFilters.add(commandFilter);
    log.info("Registered {} as a command filter", commandFilter.getClass().getSimpleName());
  }
}

