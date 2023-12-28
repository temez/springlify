package dev.temez.springlify.platform.bukkit.configuration.item;

import dev.temez.springlify.platform.bukkit.configuration.ConfigurableColor;
import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ConfigurableLeatherArmorMeta} class represents the leather armor meta information
 * for the configurable item. It includes properties such as color.
 *
 * <p>Instances of this class can be created with the specified leather armor meta information,
 * and the configured item meta can be applied to a {@link ItemBuilder.MaterialItemBuilder}
 * using the {@code apply} method.</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigurableLeatherArmorMeta
    implements ConfigurableMeta<ItemBuilder.MaterialItemBuilder.LeatherArmorBuilder> {

  /**
   * The color of the leather armor.
   */
  @NotNull
  ConfigurableColor color;

  @Override
  public ItemBuilder.@NotNull MaterialItemBuilder apply(
      @NotNull ItemBuilder.MaterialItemBuilder.LeatherArmorBuilder builder,
      Object @NotNull ... params
  ) {
    return builder.color(color.getPlatformObject()).apply();
  }
}
