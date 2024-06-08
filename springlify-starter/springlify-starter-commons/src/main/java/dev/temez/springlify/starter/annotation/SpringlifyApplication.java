package dev.temez.springlify.starter.annotation;


import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a class as a Springlify application.
 *
 * <p>This annotation is used to specify the main Spring application class that will be
 * used to initialize the Spring context within a Bukkit plugin.</p>
 *
 * <p>Usage:</p>
 * <pre>
 * &#64;SpringlifyApplication(springApplicationClass = MySpringApplication.class)
 * public class MyPlugin extends SpringlifyBukkitPlugin {
 *     // Plugin code here
 * }
 * </pre>
 *
 * @since 0.7.0.0-RC1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringlifyApplication {

  /**
   * Specifies the main Spring application class.
   *
   * @return the Spring application class
   */
  @NotNull Class<?> springApplicationClass();
}
