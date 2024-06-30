package dev.temez.springlify.platform.configuration.bukkit.item.meta;

import dev.temez.springlify.platform.configuration.bukkit.EnchantmentConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Configuration class representing the item meta properties.
 *
 * <p>This class is used for configuring the general item meta properties, such as display name, lore, enchantments, etc.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemMetaConfiguration {

  /**
   * The display name of the item.
   */
  @Nullable
  String displayName;

  /**
   * The lore of the item.
   */
  @NotNull
  List<String> lore = new ArrayList<>();

  /**
   * The custom model data of the item. Default value is 0.
   */
  int modelData = 0;

  /**
   * The durability of the item. Default value is 0.
   */
  int durability = 0;

  /**
   * The set of item flags for the item.
   */
  @NotNull
  Set<ItemFlag> itemFlags = new HashSet<>();

  /**
   * Indicates if the item is unbreakable. Default value is false.
   */
  boolean unbreakable = false;

  /**
   * The set of enchantments applied to the item.
   */
  @NotNull
  Set<EnchantmentConfiguration> enchantments = new HashSet<>();

  /**
   * The leather armor item meta configuration.
   */
  @Nullable
  @NestedConfigurationProperty
  LeatherArmorItemMetaConfiguration leatherArmorMeta;

  /**
   * The potion item meta configuration.
   */
  @Nullable
  @NestedConfigurationProperty
  PotionItemMetaConfiguration potionMeta;

  /**
   * The skull item meta configuration.
   */
  @Nullable
  @NestedConfigurationProperty
  SkullItemMetaConfiguration skullMeta;
}
