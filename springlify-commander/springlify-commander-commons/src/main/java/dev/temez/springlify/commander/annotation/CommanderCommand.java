package dev.temez.springlify.commander.annotation;

import dev.temez.springlify.commander.command.CommandType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Marks a method or class as a command in the Commander framework.
 * <p>
 * Commands annotated with {@code CommanderCommand} can be registered and executed by the Commander module.
 * This annotation provides metadata about the command such as its name, description, and aliases.
 * </p>
 *
 * @see CommandType
 * @since 0.7.0.0-RC1
 */
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CommanderCommand {

  /**
   * Specifies the type of the command.
   *
   * @return the type of the command
   */
  @NotNull CommandType type() default CommandType.SHARED;

  /**
   * Specifies the name of the command.
   *
   * @return the name of the command
   */
  @NotNull String name();

  /**
   * Provides a description of the command.
   *
   * @return the description of the command
   */
  @NotNull String description() default "";

  /**
   * Specifies alias names for the command.
   * <p>
   * Aliases can be used as alternative names to invoke the command.
   * </p>
   *
   * @return an array of alias names for the command
   */
  @NotNull String[] alias() default {};
}

