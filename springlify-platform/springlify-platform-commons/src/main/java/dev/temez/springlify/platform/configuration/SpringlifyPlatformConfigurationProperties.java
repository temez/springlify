package dev.temez.springlify.platform.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for Springlify platform.
 *
 * <p>This class defines properties related to the behavior of the Springlify platform,
 * such as the text converter mode.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("springlify.platform")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpringlifyPlatformConfigurationProperties {

  /**
   * The mode of the text converter.
   */
  @NotNull
  TextConverterMode textConverterMode = TextConverterMode.MINI_MESSAGE;

  /**
   * Enumeration representing the text converter modes.
   */
  public enum TextConverterMode {
    /**
     * Use the legacy text converter mode.
     */
    LEGACY,

    /**
     * Use the MiniMessage text converter mode.
     */
    MINI_MESSAGE
  }
}
