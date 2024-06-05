package dev.temez.springlify.platform.configuration.bukkit;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;


@Setter
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class PotionEffectConfiguration {

  @NotNull
  PotionEffectType type;

  Duration duration;

  int amplifier;

  boolean ambient = false;

  boolean particles = true;

  boolean showIcon = true;
}