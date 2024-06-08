package dev.temez.springlify.starter.annotation;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * The {@code VelocityCommand} annotation is a custom annotation used to mark classes
 * as Velocity commands, for continuous auto registering with spring post processor.
 *
 * <p>Usage example:
 * <pre>{@code
 * @VelocityComand(command="mycommand", alias = {"alias1", "alias2"})
 * public class MyCommand implements CommandExecutor {
 *
 *  // Command execution logic
 *
 * }
 * }</pre>
 *
 * <p>In this example, `MyCommand` is marked as a Velocity command with the primary command
 * "mycommand" and two aliases "alias1" and "alias2."
 *
 * @since 0.7.0.0-RC1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface BukkitCommand {

  /**
   * Alias for the {@link Component} annotation's value attribute.
   * Specifies the name of the bean.
   *
   * @return The primary bean name.
   */
  @AliasFor(annotation = Component.class)
  String value() default "";

  /**
   * Specifies the primary command name.
   *
   * @return The primary command name.
   */
  @NotNull
  String command();
}