package dev.temez.springlify.platform.configuration.bukkit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

/**
 * Configuration class representing the potion effect properties.
 *
 * <p>This class is used for configuring potion effect properties, such as the effect type, duration, amplifier, and visibility.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class PotionEffectConfiguration {

  /**
   * The type of potion effect.
   */
  @NotNull
  PotionEffectType type;

  /**
   * The duration of the potion effect.
   */
  Duration duration;

  /**
   * The amplifier level of the potion effect.
   */
  int amplifier;

  /**
   * Indicates whether the effect is ambient. Default value is false.
   */
  boolean ambient = false;

  /**
   * Indicates whether particles are shown. Default value is true.
   */
  boolean particles = true;

  /**
   * Indicates whether the effect icon is shown. Default value is true.
   */
  boolean showIcon = true;
}
