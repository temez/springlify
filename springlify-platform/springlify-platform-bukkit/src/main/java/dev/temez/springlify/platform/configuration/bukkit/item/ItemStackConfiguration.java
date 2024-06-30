package dev.temez.springlify.platform.configuration.bukkit.item;

import dev.temez.springlify.platform.configuration.bukkit.item.meta.ItemMetaConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Configuration class representing an ItemStack with its properties.
 *
 * <p>This class is used for configuring ItemStacks, including the material, amount, and item meta.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemStackConfiguration {

  /**
   * The material of the ItemStack.
   */
  @NotNull
  Material material;

  /**
   * The amount of items in the ItemStack. Default value is 1.
   */
  int amount = 1;

  /**
   * The item meta configuration for the ItemStack.
   *
   * <p>This property represents additional metadata for the ItemStack, such as display name, lore, etc.</p>
   */
  @Nullable
  @NestedConfigurationProperty
  ItemMetaConfiguration itemMeta;
}
