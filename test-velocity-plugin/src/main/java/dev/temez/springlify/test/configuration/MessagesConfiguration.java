package dev.temez.springlify.test.configuration;

import dev.temez.springlify.platform.velocity.configuration.localization.LocalizationConfiguration;
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

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "configuration.localization")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessagesConfiguration implements LocalizationConfiguration {

  @NotNull Map<String, String> messages;


  @Override
  public @Nullable String getMessage(@NotNull String key) {
    return messages.get(key);
  }
}
