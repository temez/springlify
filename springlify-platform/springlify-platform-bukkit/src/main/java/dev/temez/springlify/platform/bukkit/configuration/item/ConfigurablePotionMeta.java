package dev.temez.springlify.platform.bukkit.configuration.item;

import dev.temez.springlify.platform.bukkit.configuration.ConfigurableColor;
import dev.temez.springlify.platform.bukkit.configuration.ConfigurablePotionEffect;
import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code ConfigurablePotionMeta} class represents the potion meta information for the
 * configurable item. It includes properties such as the main effect type, effects, and color.
 *
 * <p>Instances of this class can be created with the specified potion meta information, and the
 * configured item meta can be applied to a {@link ItemBuilder.MaterialItemBuilder} using the
 * {@code apply} method.</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigurablePotionMeta
    implements ConfigurableMeta<ItemBuilder.MaterialItemBuilder.PotionBuilder> {

  /**
   * The main effect type of the potion.
   */
  @Nullable
  PotionType mainEffectType;

  /**
   * The list of configurable potion effects applied to the potion.
   */
  @NotNull
  List<ConfigurablePotionEffect> effects = new ArrayList<>();

  /**
   * The color of the potion.
   */
  @Nullable
  ConfigurableColor color;

  @Override
  public ItemBuilder.MaterialItemBuilder apply(
      @NotNull ItemBuilder.MaterialItemBuilder.PotionBuilder builder,
      Object @NotNull ... params
  ) {
    if (mainEffectType != null) {
      builder.mainEffectType(mainEffectType);
    }
    if (color != null) {
      builder.color(color.getPlatformObject());
    }
    effects
        .stream()
        .map(ConfigurablePotionEffect::getPlatformObject)
        .forEach(builder::effect);
    return builder.apply();
  }
}
