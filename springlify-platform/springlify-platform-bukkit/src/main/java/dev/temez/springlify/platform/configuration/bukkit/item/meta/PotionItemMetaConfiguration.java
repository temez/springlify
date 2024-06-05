package dev.temez.springlify.platform.configuration.bukkit.item.meta;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.PotionEffectConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class PotionItemMetaConfiguration {

  @NotNull
  List<PotionEffectConfiguration> effects = new ArrayList<>();

  @Nullable
  PotionType mainEffectType;

  @Nullable
  @NestedConfigurationProperty
  ColorConfiguration color;
}