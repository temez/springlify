package dev.temez.springlify.commons.initializer;

import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import java.util.List;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * The {@code SpringlifyInitializer} interface defines methods for configuring and initializing
 * a Spring application context within a Springlify plugin.
 */
public interface SpringlifyInitializer {

  /**
   * Gets the list of bean post processors to be registered with the Spring application context.
   *
   * @param plugin The Springlify plugin for which post processors are needed.
   * @return A list of bean post processor classes.
   */
  @NotNull List<Class<? extends BeanPostProcessor>> getPostProcessors(
      @NotNull SpringlifyPlugin plugin
  );

  /**
   * Gets the class loader to be used by the Spring application context.
   *
   * @param plugin The Springlify plugin for which the class loader is needed.
   * @return The class loader.
   */
  @NotNull ClassLoader getClassLoader(@NotNull SpringlifyPlugin plugin);

  /**
   * Configures the Spring application using a SpringApplicationBuilder.
   *
   * @param plugin The Springlify plugin for which the application is configured.
   * @return A consumer for configuring the Spring application.
   */
  @NotNull Consumer<SpringApplicationBuilder> configure(@NotNull SpringlifyPlugin plugin);

  /**
   * Configures the Spring application context using an ApplicationContextInitializer.
   *
   * @param plugin The Springlify plugin for which the context is configured.
   * @return An ApplicationContextInitializer for configuring the context.
   */
  @NotNull ApplicationContextInitializer<GenericApplicationContext> configureContext(
      @NotNull SpringlifyPlugin plugin
  );

  /**
   * Initializes and returns the Spring application context for the plugin.
   *
   * @param plugin The Springlify plugin for which the context is initialized.
   * @return The initialized Spring application context.
   */
  @NotNull ConfigurableApplicationContext initialize(@NotNull SpringlifyPlugin plugin);
}
