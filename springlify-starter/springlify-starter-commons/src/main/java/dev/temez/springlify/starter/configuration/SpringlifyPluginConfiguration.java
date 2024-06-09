package dev.temez.springlify.starter.configuration;

import dev.temez.springlify.starter.initializer.SpringlifyInitializer;
import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class for integrating Springlify plugins.
 *
 * <p>This class provides Spring beans for the {@link SpringlifyInitializer}
 * by retrieving them from the provided {@link SpringlifyPlugin} instance.</p>
 *
 * @see SpringlifyInitializer
 * @see SpringlifyPlugin
 * @since 0.7.0.0-RC1
 */
@Configuration
public class SpringlifyPluginConfiguration {


  /**
   * Provides the {@link SpringlifyInitializer} as a Spring bean.
   *
   * @param plugin the Springlify plugin instance
   * @return the Springlify initializer
   */
  @Bean
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  SpringlifyInitializer springlifyInitializer(@NotNull SpringlifyPlugin plugin) {
    return plugin.getInitializer();
  }
}
