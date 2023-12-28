package dev.temez.springlify.starter.commons.initializer;

import dev.temez.springlify.starter.commons.CompoundClassLoader;
import dev.temez.springlify.starter.commons.annotation.EnableSpringlifyStarter;
import dev.temez.springlify.starter.commons.configuraiton.ConfigurationLoader;
import dev.temez.springlify.starter.commons.configuraiton.ConfigurationLoaderImpl;
import dev.temez.springlify.starter.commons.plugin.SpringlifyPlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * The {@code AbstractSpringlifyInitializer} class is an abstract implementation of
 * {@link SpringlifyInitializer}. It provides common functionality for configuring and initializing
 * a Spring application context in a Springlify plugin.
 */
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SpringlifyInitializerImpl implements SpringlifyInitializer {

  @NotNull ConfigurationLoader configurationLoader = new ConfigurationLoaderImpl();

  /**
   * Gets the class loader to be used by the Spring application context.
   * This implementation combines the class loader of the plugin and the context class loader of
   * the current thread.
   *
   * @param plugin The Springlify plugin for which the class loader is needed.
   * @return The combined class loader.
   */
  @Override
  public @NotNull ClassLoader getClassLoader(@NotNull SpringlifyPlugin plugin) {
    List<ClassLoader> loaders = new ArrayList<>(2);
    loaders.add(plugin.getClass().getClassLoader());
    loaders.add(Thread.currentThread().getContextClassLoader());
    return new CompoundClassLoader(loaders);
  }

  /**
   * Configures the Spring application context using an ApplicationContextInitializer.
   * This implementation registers bean post processors with the context if it's an instance
   * of AnnotationConfigApplicationContext.
   *
   * @param plugin The Springlify plugin for which the context is configured.
   * @return An ApplicationContextInitializer for configuring the context.
   */
  @Override
  public @NotNull ApplicationContextInitializer<GenericApplicationContext> configureContext(
      @NotNull SpringlifyPlugin plugin) {
    return applicationContext -> {
      if (applicationContext instanceof AnnotationConfigApplicationContext context) {
        applicationContext.getBeanFactory().registerSingleton("springlifyPlugin", plugin);
        configurationLoader.load(context, plugin);
      }
    };
  }

  /**
   * Configures the Spring application using a SpringApplicationBuilder.
   * This implementation does not perform any additional configuration.
   *
   * @param plugin The Springlify plugin for which the application is configured.
   * @return A consumer for configuring the Spring application.
   */
  @Override
  public @NotNull Consumer<SpringApplicationBuilder> configure(@NotNull SpringlifyPlugin plugin) {
    return (applicationBuilder) -> {
    };
  }

  /**
   * Initializes and returns the Spring application context for the plugin.
   * This implementation builds the Spring application using a SpringApplicationBuilder.
   *
   * @param plugin The Springlify plugin for which the context is initialized.
   * @return The initialized Spring application context.
   */
  @Override
  public @NotNull ConfigurableApplicationContext initialize(@NotNull SpringlifyPlugin plugin) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(getApplicationClass(plugin))
        .bannerMode(Banner.Mode.OFF)
        .resourceLoader(new DefaultResourceLoader(getClassLoader(plugin)))
        .initializers(
            configureContext(plugin)
        );
    configure(plugin).accept(builder);

    return builder.run();
  }

  private @NotNull Class<?> getApplicationClass(@NotNull SpringlifyPlugin plugin)
      throws IllegalStateException {
    EnableSpringlifyStarter annotation =
        plugin.getClass().getAnnotation(EnableSpringlifyStarter.class);
    if (annotation == null) {
      throw new IllegalStateException("Missing @EnableSpringlifyStarter annotation!");
    }
    return annotation.applicationClass();
  }
}
