package dev.temez.springlify.platform.bukkit.configuration.item;

import dev.temez.springlify.platform.bukkit.configuration.Skin;
import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code ConfigurableSkullItemMeta} class represents the skull meta information for the
 * configurable item. It includes properties such as texture, owner, and skin.
 *
 * <p>Instances of this class can be created with the specified skull meta information, and the
 * configured item meta can be applied to a {@link ItemBuilder.MaterialItemBuilder} using the
 * {@code apply} method.</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigurableSkullItemMeta
    implements ConfigurableMeta<ItemBuilder.MaterialItemBuilder.SkullBuilder> {
  /**
   * The owner of the skull. It represents the name of the player for a player skull.
   */
  @Nullable
  String owner;

  /**
   * The skin information for the skull. It includes properties such as texture and signature.
   */
  @Nullable
  Skin skin;

  @Override
  public ItemBuilder.@NotNull MaterialItemBuilder apply(
      @NotNull ItemBuilder.MaterialItemBuilder.SkullBuilder builder,
      Object @NotNull ... params
  ) {
    if (skin != null) {
      return builder.skin(skin).apply();
    }
    if (owner != null) {
      return builder.owner(owner).apply();
    }
    return builder.apply();
  }
}
