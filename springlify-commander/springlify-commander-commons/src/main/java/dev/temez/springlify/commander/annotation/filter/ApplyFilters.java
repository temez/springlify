package dev.temez.springlify.commander.annotation.filter;

import dev.temez.springlify.commander.command.filter.simple.SimpleCommandFilter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * An annotation to apply filters to commands.
 * <p>
 * This annotation is used to specify one or more filters to be applied to individual commands.
 * </p>
 * <p>
 * The {@code SimpleCommandFilter} type parameter specifies the type of filters that can be applied.
 * </p>
 *
 * @see SimpleCommandFilter
 * @since 0.7.0.0-RC1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface ApplyFilters {

  /**
   * Specifies one or more filters to be applied to the command.
   *
   * @return An array of filter classes to be applied.
   */
  @NotNull
  Class<? extends SimpleCommandFilter>[] value();
}
