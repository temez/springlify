package dev.temez.springlify.commander.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * An annotation indicating that the annotated method serves as a sub-command.
 * This annotation is intended to be used on methods.
 *
 * @since 0.5.8.9dev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {

  /**
   * The name of the sub-command.
   *
   * @return The name of the sub-command.
   */
  @NotNull String name();

  /**
   * The description of the sub-command.
   *
   * @return The description of the sub-command.
   */
  @NotNull String description();

  /**
   * The type of the sub-command (INGAME, CONSOLE, SHARED).
   *
   * @return The type of the sub-command.
   */
  @NotNull Command.CommandType type() default Command.CommandType.SHARED;
}
