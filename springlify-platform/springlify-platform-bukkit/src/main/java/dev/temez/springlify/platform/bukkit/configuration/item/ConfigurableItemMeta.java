package dev.temez.springlify.platform.bukkit.configuration.item;

import dev.temez.springlify.platform.bukkit.configuration.ConfigurableEnchantment;
import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import dev.temez.springlify.platform.commons.utility.TextUtility;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code ConfigurableItemMeta} class represents the meta information for a configurable item.
 * It includes properties such as display name, lore, custom model data, durability, enchantments,
 * item flags, and various meta types (e.g., potion, skull, leather armor).
 *
 * @since 0.5.9.8dev
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigurableItemMeta
    implements ConfigurableMeta<ItemBuilder.MaterialItemBuilder>, Cloneable {

  /**
   * The display name of the configurable item. It represents the name that is displayed for the
   * item.
   */
  @Nullable
  String displayName;

  /**
   * The lore of the configurable item. It represents a list of strings providing additional
   * information about the item.
   */
  @NotNull
  List<String> lore = new ArrayList<>();

  /**
   * The custom model data for the configurable item. It represents the custom model data value
   * for the item.
   */
  int modelData = 0;

  /**
   * The durability of the configurable item. It represents the durability value for the item.
   */
  int durability = 0;

  /**
   * The set of item flags for the configurable item. It represents the set of item flags
   * associated with the item.
   */
  @NotNull
  Set<ItemFlag> itemFlags = new HashSet<>();

  /**
   * Indicates whether the configurable item is unbreakable. If true, the item is unbreakable;
   * otherwise, it can break.
   */
  boolean unbreakable = false;

  /**
   * The set of enchantments for the configurable item. It represents the set of enchantments
   * applied to the item.
   */
  @NotNull
  Set<ConfigurableEnchantment> enchantments = new HashSet<>();

  /**
   * The skull meta information for the configurable item. It includes properties such as texture,
   * owner, and skin.
   */
  @Nullable
  ConfigurableSkullItemMeta skullMeta;

  /**
   * The leather armor meta information for the configurable item. It includes properties such as
   * color.
   */
  @Nullable
  ConfigurableLeatherArmorMeta leatherArmorMeta;

  /**
   * The potion meta information for the configurable item. It includes properties such as main
   * effect type, effects, and color.
   */
  @Nullable
  ConfigurablePotionMeta potionMeta;

  @Override
  public ItemBuilder.@NotNull MaterialItemBuilder apply(
      @NotNull ItemBuilder.MaterialItemBuilder builder,
      Object @NotNull ... params
  ) {
    if (displayName != null) {
      builder.setName(TextUtility.applyReplacers(displayName, params));
    }
    builder
        .lore(TextUtility.applyReplacers(lore, params))
        .customModelData(modelData)
        .setdurability(durability)
        .unbreakable(unbreakable);
    itemFlags.forEach(builder::itemFlag);
    enchantments.forEach(enchantment -> builder.enchantment(
            enchantment.getEnchantment(),
            enchantment.getLevel()
        )
    );
    if (potionMeta != null) {
      return potionMeta.apply(builder.potionBuilder(), params);
    }
    if (skullMeta != null) {
      return skullMeta.apply(builder.skullBuilder(), params);
    }
    if (leatherArmorMeta != null) {
      return leatherArmorMeta.apply(builder.leatherArmorBuilder(), params);
    }
    return builder;
  }

  @Override
  @SneakyThrows
  public ConfigurableItemMeta clone() {
    return (ConfigurableItemMeta) super.clone();
  }
}
