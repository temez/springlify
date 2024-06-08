package dev.temez.springlify.commander.annotation;

import dev.temez.springlify.commander.command.sender.SenderDetailsFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * Indicates that detailed sender information support should be enabled for a command method.
 * <p>
 * Methods annotated with {@code @EnableDetailsSupport} will receive additional sender details
 * when invoked as part of a command execution.
 * </p>
 * <p>
 * The {@link SenderDetailsFactory} specified in the annotation value determines how sender details are constructed.
 * </p>
 *
 * @see SenderDetailsFactory
 * @since 0.7.0.0-RC1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SenderDetailsSource {

  /**
   * Specifies the class of the sender details factory to be used for constructing sender details.
   *
   * @return The class of the sender details factory.
   */
  @NotNull
  Class<? extends SenderDetailsFactory<?>> value();
}
