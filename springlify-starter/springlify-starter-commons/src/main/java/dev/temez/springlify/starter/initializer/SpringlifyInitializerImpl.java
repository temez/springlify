package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.annotation.SpringlifyApplication;
import dev.temez.springlify.starter.configuration.ConfigurationLoader;
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


@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SpringlifyInitializerImpl implements SpringlifyInitializer {

  @NotNull
  ClassLoaderFactory classLoaderFactory;

  @NotNull
  ConfigurationLoader configurationLoader;

  @Override
  public @NotNull ConfigurableApplicationContext initialize(@NotNull SpringlifyPlugin plugin) {
    log.debug("Discovering main application class for {}", plugin.getClass().getName());

    SpringlifyApplication annotation = plugin.getClass().getAnnotation(SpringlifyApplication.class);
    if (annotation == null) {
      throw new IllegalStateException("Missing @SpringlifyApplication annotation!");
    }

    log.debug("Initializing application context for {}", annotation.applicationClass().getName());
    SpringApplicationBuilder builder = new SpringApplicationBuilder(annotation.applicationClass())
        .bannerMode(Banner.Mode.OFF)
        .resourceLoader(new DefaultResourceLoader(classLoaderFactory.createClassLoader(plugin)))
        .initializers(applicationContext -> {
          applicationContext.getBeanFactory().registerSingleton("springlifyPlugin", plugin);
          configurationLoader.load(applicationContext, plugin);
        });

    return builder.run();
  }
}