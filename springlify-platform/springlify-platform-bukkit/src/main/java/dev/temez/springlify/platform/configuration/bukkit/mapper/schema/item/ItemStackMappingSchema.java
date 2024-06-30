package dev.temez.springlify.platform.configuration.bukkit.mapper.schema.item;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.PotionEffectConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.item.ItemStackConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.item.meta.ItemMetaConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.item.meta.LeatherArmorItemMetaConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.item.meta.PotionItemMetaConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.item.meta.SkullItemMetaConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import dev.temez.springlify.platform.item.ItemBuilder;
import dev.temez.springlify.platform.item.ItemBuilderFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Mapping schema for converting ItemStackConfiguration to ItemStack.
 *
 * <p>This component is responsible for mapping properties from {@link ItemStackConfiguration} to {@link ItemStack} instances.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ItemStackMappingSchema implements MappingSchema<ItemStackConfiguration, ItemStack> {

  /**
   * The mapping schema for ColorConfiguration to Color.
   */
  @NotNull
  MappingSchema<ColorConfiguration, Color> colorMappingSchema;

  /**
   * The mapping schema for PotionEffectConfiguration to PotionEffect.
   */
  @NotNull
  MappingSchema<PotionEffectConfiguration, PotionEffect> potionEffectMappingSchema;

  /**
   * The item builder factory.
   */
  @NotNull
  ItemBuilderFactory itemBuilderFactory;

  /**
   * Maps an ItemStackConfiguration instance to an ItemStack instance.
   *
   * @param source The source ItemStackConfiguration to map from.
   * @return The resulting ItemStack mapped from the configuration.
   */
  @Override
  public @NotNull ItemStack map(@NotNull ItemStackConfiguration source) {
    ItemBuilder.MaterialItemBuilder builder = itemBuilderFactory.newBuilder(source.getMaterial())
        .amount(source.getAmount());

    ItemMetaConfiguration itemMetaConfiguration = source.getItemMeta();
    if (itemMetaConfiguration != null) {

      if (itemMetaConfiguration.getDisplayName() != null) {
        itemMetaConfiguration.setDisplayName(itemMetaConfiguration.getDisplayName());
      }
      builder
          .lore(itemMetaConfiguration.getLore())
          .enchantments(itemMetaConfiguration.getEnchantments())
          .customModelData(itemMetaConfiguration.getModelData())
          .setDurability(itemMetaConfiguration.getDurability())
          .itemFlag(itemMetaConfiguration.getItemFlags())
          .unbreakable(itemMetaConfiguration.isUnbreakable());

      LeatherArmorItemMetaConfiguration leatherArmorItemMetaConfiguration = itemMetaConfiguration.getLeatherArmorMeta();
      if (leatherArmorItemMetaConfiguration != null) {
        builder = builder
            .leatherArmorBuilder()
            .color(colorMappingSchema.map(leatherArmorItemMetaConfiguration.getColor()))
            .apply();
      }

      SkullItemMetaConfiguration skullItemMetaConfiguration = itemMetaConfiguration.getSkullMeta();
      if (skullItemMetaConfiguration != null) {
        if (skullItemMetaConfiguration.getOwner() != null) {
          builder = builder.skullBuilder().owner(skullItemMetaConfiguration.getOwner()).apply();
        } else if (skullItemMetaConfiguration.getSkin() != null) {
          builder = builder.skullBuilder().skin(skullItemMetaConfiguration.getSkin()).apply();
        }
      }

      PotionItemMetaConfiguration potionItemMetaConfiguration = itemMetaConfiguration.getPotionMeta();
      if (potionItemMetaConfiguration != null) {
        ItemBuilder<ItemBuilder.MaterialItemBuilder>.PotionBuilder potionBuilder = builder.potionBuilder();
        potionBuilder.effects(potionEffectMappingSchema.map(potionItemMetaConfiguration.getEffects()));
        if (potionItemMetaConfiguration.getMainEffectType() != null) {
          potionBuilder.mainEffectType(potionItemMetaConfiguration.getMainEffectType());
        }
        if (potionItemMetaConfiguration.getColor() != null) {
          potionBuilder.color(colorMappingSchema.map(potionItemMetaConfiguration.getColor()));
        }
        builder = potionBuilder.apply();
      }
    }

    return builder.build();
  }
}
