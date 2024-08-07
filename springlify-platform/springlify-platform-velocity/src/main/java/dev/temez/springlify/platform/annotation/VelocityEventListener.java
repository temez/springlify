package dev.temez.springlify.platform.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code VelocityEventHandler} annotation is a custom annotation used to mark classes as event
 * handlers when developing plugins for Velocity.
 *
 * <p>This annotation is used to declare that a class is an event listener,
 * and should be registered.
 *
 * <p>Usage example:
 * <pre>{@code
 * @Service
 * @VelocityEventHandler
 * public class MyListenableService {
 *
 *  @Subscribe
 *  private void onEvent(@NotNull PlayerJoinEvent event) {
 *    //event handling logic
 *  }
 *
 * }
 * }</pre>
 *
 * <p>In this example, `MyListenableService` is marked as an event handler class, and the
 * `onPlayerJoin` method is an event handler for the "PlayerJoinEvent."
 *
 * @since 0.7.1.0-SNAPSHOT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface VelocityEventListener {


  /**
   * Alias for the {@link Component} annotation's value attribute.
   * Specifies the name of the bean.
   *
   * @return The primary bean name.
   */
  @AliasFor(annotation = Component.class)
  String value() default "";
}