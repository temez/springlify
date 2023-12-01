package dev.temez.springlify.commons.initializer;

import dev.temez.springlify.commons.CompoundClassLoader;
import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Consumer;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * The {@code AbstractSpringlifyInitializer} class is an abstract implementation of
 * {@link SpringlifyInitializer}. It provides common functionality for configuring and initializing
 * a Spring application context in a Springlify plugin.
 */
@Log4j2
public class SpringlifyInitializerImpl implements SpringlifyInitializer {

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
        loadExternalConfigurations(context, plugin);
      }
    };
  }

  private void loadExternalConfigurations(
      @NotNull ConfigurableApplicationContext context,
      @NotNull SpringlifyPlugin plugin
  ) {
    YamlPropertiesFactoryBean propertiesFactory = new YamlPropertiesFactoryBean();

    File defaultConfig = new File(plugin.getDataFolder(), "application.yml");
    if (!defaultConfig.exists()) {
      throw new IllegalStateException("application.yml not found in plugin directory!");
    }


    List<Resource> resourceList = new ArrayList<>();

    resourceList.add(
        new FileSystemResource(
            new File(plugin.getDataFolder(), "application.yml")
        )
    );

    propertiesFactory.setResources(
        resourceList.toArray(new Resource[0])
    );

    updateActiveProfiles(
        Objects.requireNonNull(propertiesFactory.getObject()),
        context.getEnvironment()
    );

    resourceList.addAll(Arrays.stream(Objects.requireNonNull(plugin.getDataFolder().listFiles()))
        .filter(file -> Files.isRegularFile(file.toPath()))
        .filter(file -> getFileExtension(file).equals("yml"))
        .filter(file -> Arrays
            .stream(context.getEnvironment().getActiveProfiles())
            .toList()
            .contains(getClearFileName(file).replace("application-", "")
            )
        )
        .toList()
        .stream()
        .map(FileSystemResource::new)
        .toList()
    );

    log.info(
        "Configurations to proceed: {}",
        String.join(", ", resourceList.stream().map(Resource::getFilename).toList()));


    propertiesFactory.setResources(
        resourceList.toArray(new Resource[0])
    );


    context.getEnvironment()
        .getPropertySources()
        .addFirst(
            new PropertiesPropertySource(
                "properties",
                Objects.requireNonNull(propertiesFactory.getObject())
            )
        );
  }

  private @NotNull String getFileExtension(@NotNull File file) {
    return file.getName().split("\\.")[1];
  }

  private @NotNull String getClearFileName(@NotNull File file) {
    return file.getName().split("\\.")[0];
  }

  private void updateActiveProfiles(
      @NotNull Properties defaultProperties,
      @NotNull ConfigurableEnvironment environment
  ) {
    String profilesString = defaultProperties.getProperty("spring.profiles.active");
    if (profilesString == null) {
      return;
    }
    environment.setActiveProfiles(
        profilesString.replace(" ", "").split(",")
    );
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
  @NotNull
  @SuppressWarnings("all")
  public ConfigurableApplicationContext initialize(@NotNull SpringlifyPlugin plugin) {
    Class<?> applicationClass = plugin.getApplicationClass();

    SpringApplicationBuilder builder = new SpringApplicationBuilder(applicationClass)
        .bannerMode(Banner.Mode.OFF)
        .resourceLoader(new DefaultResourceLoader(getClassLoader(plugin)))
        .initializers(
            configureContext(plugin)
        );

    configure(plugin).accept(builder);

    return builder.run();
  }
}
