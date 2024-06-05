package dev.temez.springlify.platform.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("springlify.platform")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpringlifyPlatformConfigurationProperties {

  @NotNull
  TextConverterMode textConverterMode = TextConverterMode.MINI_MESSAGE;

  public enum TextConverterMode {
    LEGACY,
    MINI_MESSAGE
  }
}
