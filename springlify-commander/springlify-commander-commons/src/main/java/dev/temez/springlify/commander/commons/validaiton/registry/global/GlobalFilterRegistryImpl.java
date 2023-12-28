package dev.temez.springlify.commander.commons.validaiton.registry.global;

import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import dev.temez.springlify.platform.commons.exception.FormattedException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link GlobalFilterRegistry} for managing global filters applicable
 * to multiple commands.
 *
 * @since 0.5.9.8dev
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class GlobalFilterRegistryImpl implements GlobalFilterRegistry {

  @NotNull
  List<SimpleCommandFilter> globalCommandFilters = new ArrayList<>();

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull @Unmodifiable List<SimpleCommandFilter> getRegisteredFilters() {
    return List.copyOf(globalCommandFilters);
  }

  /**
   * {@inheritDoc}
   *
   * @param filter The global filter to be registered.
   * @throws FormattedException If the filter is already registered in the global filter registry.
   */
  @Override
  public void register(@NotNull SimpleCommandFilter filter) throws FormattedException {
    if (globalCommandFilters.contains(filter)) {
      throw new FormattedException(
          "%s already registered in global filter registry!",
          filter.getClass().getSimpleName()
      );
    }
    globalCommandFilters.add(filter);
    log.info("Registered {} as a global command filter", filter.getClass().getSimpleName());
  }
}
