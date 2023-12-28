package dev.temez.springlify.commander.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * An annotation indicating that the annotated type represents a command.
 * This annotation is intended to be used on classes, interfaces, enums, etc.
 *
 * @since 0.5.8.9dev
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

  /**
   * The type of the command (INGAME, CONSOLE, SHARED).
   *
   * @return The type of the command.
   */
  @NotNull CommandType type() default CommandType.SHARED;

  /**
   * The name of the command.
   *
   * @return The name of the command.
   */
  @NotNull String name();

  /**
   * The description of the command.
   *
   * @return The description of the command.
   */
  @NotNull String description();

  /**
   * The aliases for the command.
   *
   * @return The aliases for the command.
   */
  @NotNull String[] alias() default {};

  /**
   * Enumeration representing the type of the command.
   */
  enum CommandType {
    INGAME,
    CONSOLE,
    SHARED
  }
}

