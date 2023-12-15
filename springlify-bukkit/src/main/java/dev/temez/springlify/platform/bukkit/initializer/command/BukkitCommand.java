package dev.temez.springlify.platform.bukkit.initializer.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * The {@code VelocityCommand} annotation is a custom annotation used to mark classes
 * as Velocity commands, for continuous auto registering with spring post processor.
 *
 * <p>Usage example:
 * <pre>{@code
 * @VelocityComand(command="mycommand, alias = {"alias1", "alias2"})
 * public class MyCommand {
 *
 *  // Command execution logic
 *
 * }
 * }</pre>
 *
 * <p>In this example, `MyCommand` is marked as a Velocity command with the primary command
 * "mycommand" and two aliases "alias1" and "alias2."
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
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