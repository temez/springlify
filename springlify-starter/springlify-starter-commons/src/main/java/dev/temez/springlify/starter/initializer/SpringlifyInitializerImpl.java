package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.annotation.SpringlifyApplication;
import dev.temez.springlify.starter.configuration.loader.ConfigurationLoader;
import dev.temez.springlify.starter.initializer.loader.ClassLoaderFactory;
import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * Implementation of {@link SpringlifyInitializer} for initializing the Spring context of Springlify plugins.
 *
 * <p>This class uses Spring Boot's {@link SpringApplicationBuilder} to initialize the Spring context
 * for the specified {@link SpringlifyPlugin}. It loads the main application class specified in the
 * {@link SpringlifyApplication} annotation and configures it with the provided {@link ClassLoaderFactory}
 * and {@link ConfigurationLoader}.</p>
 *
 * @see SpringlifyInitializer
 * @see SpringlifyPlugin
 * @see SpringlifyApplication
 * @since 0.7.0.0-RC1
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SpringlifyInitializerImpl implements SpringlifyInitializer {

  @NotNull
  ClassLoaderFactory classLoaderFactory;

  @NotNull
  ConfigurationLoader configurationLoader;

  /**
   * {@inheritDoc}
   *
   * @param plugin the Springlify plugin instance
   * @return the initialized Spring application context
   * @throws IllegalStateException if the plugin is missing the {@code @SpringlifyApplication} annotation
   */
  @Override
  public @NotNull ConfigurableApplicationContext initialize(@NotNull SpringlifyPlugin plugin) throws IllegalStateException {
    log.debug("Discovering main application class for {}", plugin.getClass().getName());

    SpringlifyApplication annotation = plugin.getClass().getAnnotation(SpringlifyApplication.class);
    if (annotation == null) {
      throw new IllegalStateException("Missing @SpringlifyApplication annotation!");
    }

    // issue #32: ctx cl must be overridden to plugin cl
    Thread.currentThread().setContextClassLoader(plugin.getPluginClassLoader());

    log.debug("Initializing application context for {}", annotation.springApplicationClass().getName());
    SpringApplicationBuilder builder = new SpringApplicationBuilder(annotation.springApplicationClass())
        .bannerMode(Banner.Mode.OFF)
        .resourceLoader(new DefaultResourceLoader(classLoaderFactory.createClassLoader(plugin)))
        .initializers(applicationContext -> {
          applicationContext.getBeanFactory().registerSingleton("springlifyPlugin", plugin);
          configurationLoader.load(applicationContext, plugin);
        });

    return builder.run();
  }
}
