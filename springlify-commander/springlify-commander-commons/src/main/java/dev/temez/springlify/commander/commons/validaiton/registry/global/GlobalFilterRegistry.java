package dev.temez.springlify.commander.commons.validaiton.registry.global;

import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import dev.temez.springlify.platform.commons.exception.FormattedException;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * Registry interface for managing global filters applicable to multiple commands.
 *
 * @since 0.5.8.dev
 */
public interface GlobalFilterRegistry {

  /**
   * Retrieves an unmodifiable list of registered global filters.
   *
   * @return An unmodifiable list of registered global filters.
   */
  @NotNull
  @Unmodifiable
  List<SimpleCommandFilter> getRegisteredFilters();

  /**
   * Registers a global filter.
   *
   * @param filter The global filter to be registered.
   * @throws FormattedException If an exception occurs during the registration process.
   */
  void register(@NotNull SimpleCommandFilter filter) throws FormattedException;
}
