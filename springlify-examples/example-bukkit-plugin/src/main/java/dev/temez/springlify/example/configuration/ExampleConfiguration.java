package dev.temez.springlify.example.configuration;

import dev.temez.springlify.platform.configuration.bukkit.item.ItemStackConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("example.plugin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExampleConfiguration {

  @NestedConfigurationProperty
  @NotNull
  ItemStackConfiguration itemStackConfiguration;


}
