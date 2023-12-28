package dev.temez.springlify.commander.commons.annotation.validation;

import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * An annotation indicating the filters to be applied to a command.
 * This annotation is intended to be used on other annotations.
 *
 * @since 0.5.8.9dev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface ApplyFilters {

  /**
   * The array of filter classes to be applied.
   *
   * @return The filter classes.
   */
  @NotNull Class<? extends SimpleCommandFilter>[] value();
}
