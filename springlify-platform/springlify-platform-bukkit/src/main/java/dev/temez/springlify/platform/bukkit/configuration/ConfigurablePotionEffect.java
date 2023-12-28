package dev.temez.springlify.platform.bukkit.configuration;

import dev.temez.springlify.platform.commons.configuration.Configurable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ConfigurablePotionEffect} class represents a configurable potion effect that can be
 * used in various platforms such as Bukkit.
 *
 * <p>Instances of this class allow specifying the type, duration, amplifier, and additional
 * settings for a potion effect. It implements the {@code PlatformConfigurable} interface,
 * enabling easy conversion to the platform-specific potion effect.</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigurablePotionEffect implements Configurable<PotionEffect> {

  /**
   * The type of potion effect.
   */
  @NotNull
  PotionEffectType type;

  /**
   * The duration of the potion effect in ticks.
   */
  int duration;

  /**
   * The amplifier or potency of the potion effect.
   */
  int amplifier;

  /**
   * Whether the potion effect is ambient (does not show particles).
   */
  boolean ambient;

  /**
   * Whether particles are shown for the potion effect.
   */
  boolean particles = true;

  /**
   * Whether the potion effect has an icon displayed.
   */
  boolean icon = true;

  /**
   * Converts this configurable potion effect to a platform-specific potion effect.
   *
   * @return The converted potion effect.
   */
  @Override
  public @NotNull PotionEffect getPlatformObject(Object @NotNull ... params) {
    return new PotionEffect(type, duration, amplifier, ambient, particles, icon);
  }
}