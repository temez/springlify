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

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class ItemMetaConfiguration {

  @Nullable
  String displayName;

  @NotNull
  List<String> lore = new ArrayList<>();

  int modelData = 0;

  int durability = 0;

  @NotNull
  Set<ItemFlag> itemFlags = new HashSet<>();

  boolean unbreakable = false;

  @NotNull
  Set<EnchantmentConfiguration> enchantments = new HashSet<>();

  @Nullable
  @NestedConfigurationProperty
  LeatherArmorItemMetaConfiguration leatherArmorMeta;

  @Nullable
  @NestedConfigurationProperty
  PotionItemMetaConfiguration potionMeta;

  @Nullable
  @NestedConfigurationProperty
  SkullItemMetaConfiguration skullMeta;

}
