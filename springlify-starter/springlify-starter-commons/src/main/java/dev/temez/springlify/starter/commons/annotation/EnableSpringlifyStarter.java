package dev.temez.springlify.starter.commons.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code EnableSpringlifyStarter} annotation is used to enable the Springlify Starter plugin
 * functionality for a specific application class.
 *
 * <p>It is used to specify the application class that should be started with the Springlify Starter
 * plugin. This annotation should be applied to the main class of the plugin.</p>
 *
 * @since 0.5.9.8dev
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableSpringlifyStarter {

  /**
   * The application class that should be started with the Springlify Starter plugin.
   *
   * @return The application class.
   */
  @NotNull Class<?> applicationClass();
}