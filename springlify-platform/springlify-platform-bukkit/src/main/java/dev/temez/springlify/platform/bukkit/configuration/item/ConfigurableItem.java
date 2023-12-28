package dev.temez.springlify.platform.bukkit.configuration.item;

import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import dev.temez.springlify.platform.commons.configuration.Configurable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code ConfigurableItem} class represents an item stack in Minecraft that can be configured
 * with specific properties, such as material, meta information, and amount. It implements the
 * {@code PlatformConfigurable} interface, allowing it to be used as a configurable component with
 * a platform-specific object.
 *
 * <p>Instances of this class can be created with the specified material, meta information, and
 * amount, and the configured item stack can be retrieved using the {@code getPlatformObject}
 * method.</p>
 *
 * <p>The configuration includes properties such as display name, lore, custom model data,
 * durability, enchantments, item flags, and various meta types (e.g., potion, skull, leather
 * armor).</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ConfigurableItem implements Configurable<ItemStack> {

  /**
   * The material of the configurable item. It specifies the type of material for the item stack.
   */
  @NotNull Material material;

  /**
   * The meta information for the configurable item. It includes properties such as display name,
   * lore, custom model data, durability, enchantments, item flags, and various meta types
   * (e.g., potion, skull, leather armor).
   */
  @Nullable ConfigurableItemMeta meta;

  /**
   * The amount of items in the stack. It represents the quantity of items for the configured
   * item stack.
   */
  int amount = 1;

  @Override
  public @NotNull ItemStack getPlatformObject(Object @NotNull ... params) {
    ItemBuilder.MaterialItemBuilder builder = ItemBuilder
        .fromMaterial(material)
        .amount(amount);

    if (meta != null) {
      return meta.apply(builder, params).build();
    }
    return builder.build();
  }

}
