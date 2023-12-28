package dev.temez.springlify.test.configuration;

import dev.temez.springlify.platform.bukkit.configuration.item.ConfigurableItem;
import dev.temez.springlify.platform.bukkit.configuration.localization.LocalizationConfiguration;
import dev.temez.springlify.starter.commons.event.impl.ContextPreShutdownEvent;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "configuration.localization")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessagesConfiguration implements LocalizationConfiguration {

  @NotNull Map<String, String> messages;

  @NotNull Map<String, ConfigurableItem> items;

  @EventListener(classes = ContextPreShutdownEvent.class)
  private void shutdown() {
    System.out.println(items);
    items.values()
        .stream()
        .map(i -> i.getPlatformObject("<worth>", 30))
        .forEach(System.out::println);
  }

  @Override
  public @Nullable String getMessage(@NotNull String key) {
    return messages.get(key);
  }

  @Override
  public @Nullable ConfigurableItem getItem(@NotNull String key) {
    return items.get(key);
  }
}
