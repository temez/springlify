package dev.temez.springlify.example.configuration;

import dev.temez.springlify.platform.configuration.bukkit.item.ItemStackConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ExampleConfiguration {

  @Bean
  MiniMessage miniMessage() {
    return MiniMessage.miniMessage();
  }


}
