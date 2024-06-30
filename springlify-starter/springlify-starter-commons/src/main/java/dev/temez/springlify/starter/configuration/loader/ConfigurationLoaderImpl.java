package dev.temez.springlify.starter.configuration.loader;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConfigurationLoaderImpl implements ConfigurationLoader {

  @NotNull
  YamlPropertiesFactoryBean propertiesFactory = new YamlPropertiesFactoryBean();

  @Override
  public void load(
      @NotNull ConfigurableApplicationContext context,
      @NotNull SpringlifyPlugin plugin
  ) throws IllegalStateException {
    saveDefaultsConfigurations(plugin);

    File rootConfiguration = new File(plugin.getDataFolder(), "application.yml");
    if (!rootConfiguration.exists()) {
      return;
    }

    List<Resource> resourcesList = new ArrayList<>();
    resourcesList.add(new FileSystemResource(rootConfiguration));

    propertiesFactory.setResources(resourcesList.toArray(new Resource[0]));

    loadActiveProfiles(
        Objects.requireNonNull(propertiesFactory.getObject()),
        context.getEnvironment()
    );
    Arrays.stream(Objects.requireNonNull(plugin.getDataFolder().listFiles()))
        .filter(file -> !getClearFileName(file).contains("application"))
        .filter(file -> Files.isRegularFile(file.toPath()))
        .filter(file -> getFileExtension(file).equals("yml"))
        .map(FileSystemResource::new)
        .forEach(resourcesList::add);

    Arrays.stream(Objects.requireNonNull(plugin.getDataFolder().listFiles()))
        .filter(file -> Files.isRegularFile(file.toPath()))
        .filter(file -> getFileExtension(file).equals("yml"))
        .filter(file -> getClearFileName(file).contains("application"))
        .filter(file -> Arrays
            .stream(context.getEnvironment().getActiveProfiles())
            .anyMatch(profile -> getClearFileName(file).contains("-" + profile))
        )
        .map(FileSystemResource::new)
        .forEach(resourcesList::add);

    log.info(
        "Processing configurations: {}",
        String.join(", ", resourcesList.stream().map(Resource::getFilename).toList()));

    propertiesFactory.setResources(
        resourcesList.toArray(new Resource[0])
    );

    PropertiesPropertySource propertySource = new PropertiesPropertySource(
        "properties",
        Objects.requireNonNull(propertiesFactory.getObject())
    );

    context.getEnvironment()
        .getPropertySources()
        .addFirst(propertySource);
  }

  private @NotNull String getFileExtension(@NotNull File file) {
    return file.getName().split("\\.")[1];
  }

  private @NotNull String getClearFileName(@NotNull File file) {
    return file.getName().split("\\.")[0];
  }

  private void loadActiveProfiles(
      @NotNull Properties defaultProperties,
      @NotNull ConfigurableEnvironment environment
  ) {
    Optional
        .ofNullable(defaultProperties.getProperty("spring.profiles.active"))
        .ifPresent(string -> environment.setActiveProfiles(
            string.replace(" ", "").split(",")
        ));
  }

  @SuppressWarnings({"ResultOfMethodCallIgnored", "resource"})
  private void saveDefaultsConfigurations(
      @NotNull SpringlifyPlugin plugin
  ) {
    File dataFolder = plugin.getDataFolder();
    if (!dataFolder.exists()) {
      dataFolder.mkdir();
    }
    new Reflections(Scanners.Resources).getResources(".*\\.yml")
        .stream()
        .filter(name -> getClass().getClassLoader().getResourceAsStream(name) != null)
        .filter(name -> !name.equals("plugin.yml"))
        .filter(name -> !new File(dataFolder, name).exists())
        .forEach(resourceName -> saveDefaultConfiguration(resourceName, plugin));
  }

  @SneakyThrows
  private void saveDefaultConfiguration(
      @NotNull String resourceName,
      @NotNull SpringlifyPlugin plugin
  ) {
    File result = new File(plugin.getDataFolder(), resourceName);
    FileCopyUtils.copy(
        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resourceName)),
        new FileOutputStream(result)
    );
  }
}
