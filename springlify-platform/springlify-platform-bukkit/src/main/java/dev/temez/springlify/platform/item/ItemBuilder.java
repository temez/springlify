package dev.temez.springlify.platform.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.temez.springlify.platform.configuration.bukkit.EnchantmentConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.SkinTextureConfiguration;
import dev.temez.springlify.platform.text.converter.TextConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Abstract class representing an item builder.
 *
 * <p>This class provides methods for constructing ItemStack objects with various properties and modifications.</p>
 *
 * @param <B> the type of the item builder
 * @since 0.7.0.0-RC1
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class ItemBuilder<B extends ItemBuilder<?>> {
  private static @NonFinal TextConverter converter;
  private final List<Function<ItemStack, ItemStack>> itemChangers = new ArrayList<>();
  private final List<Consumer<ItemStack>> itemModifications = new ArrayList<>();
  private final List<Consumer<ItemMeta>> metaModifications = new ArrayList<>();
  private final List<Consumer<NBTItem>> nbtModifications = new ArrayList<>();

  public static void setTextConverter(@NotNull TextConverter converter) throws UnsupportedOperationException {
    if (ItemBuilder.converter != null) {
      throw new UnsupportedOperationException("Text converter have already been initialized!");
    }
    ItemBuilder.converter = converter;
  }

  @Contract("_ -> new")
  public static @NotNull MaterialItemBuilder fromMaterial(@NotNull Material material) {
    return new MaterialItemBuilder(material);
  }

  @Contract("_ -> new")
  public static @NotNull ItemStackItemBuilder fromItem(@NotNull ItemStack itemStack) {
    return new ItemStackItemBuilder(itemStack);
  }

  public @NotNull B setName(@NotNull String name) {
    metaModifications.add(
        meta -> meta.displayName(converter.getResetComponent().append(converter.parse(name))));
    return getThis();
  }

  public @NotNull B customModelData(int customModelData) {
    metaModifications.add(meta -> meta.setCustomModelData(customModelData));
    return getThis();
  }

  public @NotNull B lore(@NotNull Collection<String> lore) {
    List<Component> componentLore = lore
        .stream()
        .map(s -> converter.getResetComponent().append(converter.parse(s)))
        .toList();
    metaModifications.add(meta -> meta.lore(componentLore));
    return getThis();
  }

  public @NotNull B appendLore(String @NotNull ... lines) {
    List<Component> componentLore = Arrays.stream(lines)
        .map(s -> converter.getResetComponent().append(converter.parse(s)))
        .toList();
    metaModifications.add(meta -> {
      List<Component> lore = getLore(meta);
      lore.addAll(componentLore);
      meta.lore(lore);
    });
    return getThis();
  }

  private @NotNull List<Component> getLore(@NotNull ItemMeta meta) {
    List<Component> lore = meta.lore();
    return lore == null ? new ArrayList<>() : lore;
  }

  public @NotNull B amount(int amount) {
    itemModifications.add(item -> item.setAmount(amount));
    return getThis();
  }

  public @NotNull B nbtModification(@NotNull Consumer<NBTItem> itemConsumer) {
    nbtModifications.add(itemConsumer);
    return getThis();
  }

  public @NotNull B setDurability(short durability) {
    metaModifications.add(meta -> ((Damageable) meta).setDamage(durability));
    return getThis();
  }

  public @NotNull B setDurability(int durability) {
    return setDurability((short) durability);
  }

  public @NotNull B unbreakable(boolean unbreakable) {
    metaModifications.add(meta -> meta.setUnbreakable(unbreakable));
    return getThis();
  }

  public @NotNull B itemFlag(@NotNull ItemFlag itemFlag) {
    metaModifications.add(meta -> meta.addItemFlags(itemFlag));
    return getThis();
  }

  public @NotNull B itemFlag(@NotNull Collection<ItemFlag> flags) {
    flags.forEach(this::itemFlag);
    return getThis();
  }

  public @NotNull B enchantment(@NotNull Enchantment enchantment, int level, boolean ignorLevelRestriction) {
    metaModifications.add(meta -> meta.addEnchant(enchantment, level, ignorLevelRestriction));
    return getThis();
  }

  public @NotNull B enchantment(@NotNull EnchantmentConfiguration enchantment) {
    metaModifications.add(meta -> meta.addEnchant(enchantment.getEnchantment(), enchantment.getLevel(), enchantment.isIgnoreLevelRestriction()));
    return getThis();
  }

  public @NotNull B enchantments(@NotNull Collection<EnchantmentConfiguration> enchantments) {
    enchantments.forEach(this::enchantment);
    return getThis();
  }


  public @NotNull LeatherArmorBuilder leatherArmorBuilder() {
    return new LeatherArmorBuilder();
  }

  public @NotNull PotionBuilder potionBuilder() {
    return new PotionBuilder();
  }

  public @NotNull BannerBuilder bannerBuilder() {
    return new BannerBuilder();
  }

  public @NotNull SkullBuilder skullBuilder() {
    return new SkullBuilder();
  }

  @SuppressWarnings("unchecked")
  protected @NotNull B getThis() {
    return (B) this;
  }

  protected abstract @NotNull ItemStack getItemStack();

  public @NotNull ItemStack build() {

    ItemStack item = getItemStack();
    itemModifications.forEach(modification -> modification.accept(item));

    ItemMeta meta = item.getItemMeta();
    metaModifications.forEach(modification -> modification.accept(meta));
    item.setItemMeta(meta);

    NBTItem nbtWrappedItem = new NBTItem(item);
    nbtModifications.forEach(modification -> modification.accept(nbtWrappedItem));

    return nbtWrappedItem.getItem();
  }


  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class MaterialItemBuilder extends ItemBuilder<MaterialItemBuilder> {

    @NotNull
    private Material material;

    @NotNull
    public MaterialItemBuilder material(@NotNull Material material) {
      this.material = material;
      return getThis();
    }

    @Override
    protected @NotNull ItemStack getItemStack() {
      return new ItemStack(material);
    }
  }

  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ItemStackItemBuilder extends ItemBuilder<ItemStackItemBuilder> {

    @Getter(AccessLevel.PROTECTED)
    private ItemStack itemStack;

    public @NotNull ItemStackItemBuilder itemStack(@NotNull ItemStack itemStack) {
      this.itemStack = itemStack;
      return getThis();
    }

  }

  @FieldDefaults(level = AccessLevel.PRIVATE)
  public class LeatherArmorBuilder {

    @Nullable
    Color color;

    public @NotNull LeatherArmorBuilder color(@NotNull Color color) {
      this.color = color;
      return this;
    }

    public @NotNull B apply() {
      if (color != null) {
        metaModifications.add(meta -> {
          LeatherArmorMeta armorMeta = (LeatherArmorMeta) meta;
          armorMeta.setColor(color);
        });
      }
      return getThis();
    }
  }

  @FieldDefaults(level = AccessLevel.PRIVATE)
  public class PotionBuilder {

    @NotNull
    final List<PotionEffect> effects = new ArrayList<>();

    @Nullable
    PotionType mainEffectType;

    @Nullable
    Color color;

    public @NotNull PotionBuilder mainEffectType(@NotNull PotionType type) {
      mainEffectType = type;
      return this;
    }

    public @NotNull PotionBuilder effect(@NotNull PotionEffect effect) {
      effects.add(effect);
      return this;
    }

    public @NotNull PotionBuilder effects(@NotNull Collection<PotionEffect> effects) {
      effects.forEach(this::effect);
      return this;
    }

    public @NotNull PotionBuilder color(@NotNull Color color) {
      this.color = color;
      return this;
    }

    public @NotNull B apply() {
      if (mainEffectType != null) {
        metaModifications.add(
            meta -> ((PotionMeta) meta).setBasePotionData(new PotionData(mainEffectType)));
      }
      metaModifications.add(
          meta -> effects.forEach(effect -> ((PotionMeta) meta).addCustomEffect(effect, true))
      );
      if (color != null) {
        metaModifications.add(meta -> ((PotionMeta) meta).setColor(color));
      }
      return getThis();
    }
  }


  @FieldDefaults(level = AccessLevel.PRIVATE)
  public class SkullBuilder {

    @Nullable
    String owner;

    @Nullable
    SkinTextureConfiguration skin;

    public @NotNull SkullBuilder owner(@NotNull Player owner) {
      this.owner = owner.getName();
      return this;
    }

    public @NotNull SkullBuilder owner(@NotNull String owner) {
      this.owner = owner;
      return this;
    }

    public @NotNull SkullBuilder skin(@NotNull SkinTextureConfiguration skin) {
      this.skin = skin;
      return this;
    }

    public @NotNull B apply() {
      if (owner != null) {
        metaModifications.add(meta -> ((SkullMeta) meta).setOwner(owner));
      }
      if (skin != null) {
        metaModifications.add(meta -> {
          SkullMeta skullMeta = (SkullMeta) meta;
          GameProfile profile = new GameProfile(UUID.randomUUID(), null);
          Property skinProperty = new Property("textures", skin.getTexture(), skin.getSignature());
          profile.getProperties().put("textures", skinProperty);
          setSkullMetaProfile(skullMeta, profile);
        });
      }
      return getThis();
    }

    @SneakyThrows
    private void setSkullMetaProfile(@NotNull SkullMeta meta, @NotNull GameProfile profile) {
      Method setProfileMethod = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
      setProfileMethod.setAccessible(true);
      setProfileMethod.invoke(meta, profile);
      setProfileMethod.setAccessible(false);
    }
  }

  @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
  public class BannerBuilder {

    @NotNull
    List<Pattern> patterns = new ArrayList<>();

    public @NotNull BannerBuilder pattern(@NotNull Pattern pattern) {
      patterns.add(pattern);
      return this;
    }

    public @NotNull BannerBuilder pattern(@NotNull DyeColor color, @NotNull PatternType type) {
      return pattern(new Pattern(color, type));
    }

    public @NotNull B apply() {
      if (!patterns.isEmpty()) {
        metaModifications.add(meta -> {
          BannerMeta bannerMeta = (BannerMeta) meta;
          bannerMeta.setPatterns(patterns);
        });
      }
      return getThis();
    }
  }
}