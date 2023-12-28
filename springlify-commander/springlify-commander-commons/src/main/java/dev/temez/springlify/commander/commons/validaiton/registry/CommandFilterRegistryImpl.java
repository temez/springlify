package dev.temez.springlify.commander.commons.validaiton.registry;

import dev.temez.springlify.commander.commons.exception.FormattedException;
import dev.temez.springlify.commander.commons.validaiton.CommandFilter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandFilterRegistryImpl implements CommandFilterRegistry {

  @NotNull List<CommandFilter<?>> registeredFilters = new ArrayList<>();

  @NotNull ApplicationContext applicationContext;

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
                "No filter for %s found in registry!",
                filterAnnotation.getSimpleName()
            )
        );
  }

  @Override
  public void register(@NotNull CommandFilter<?> commandFilter) throws FormattedException {
    if (registeredFilters.contains(commandFilter)) {
      throw new FormattedException(
          "%s already registered in registry!",
          commandFilter.getClass().getSimpleName()
      );
    }
    registeredFilters.add(commandFilter);
    log.info("Registered {} as command filter", commandFilter.getClass().getSimpleName());
  }
}
