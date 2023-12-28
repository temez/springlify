package dev.temez.springlify.commander.commons.annotation;

import dev.temez.springlify.commander.commons.sender.SenderDetailsFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * An annotation indicating that the annotated method enables details support.
 * This annotation is intended to be used on methods.
 *
 * @since 0.5.8.9dev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnableDetailsSupport {

  /**
   * The class implementing the SenderDetailsFactory interface.
   *
   * @return The class implementing the SenderDetailsFactory interface.
   */
  @NotNull Class<? extends SenderDetailsFactory<?>> value();
}
