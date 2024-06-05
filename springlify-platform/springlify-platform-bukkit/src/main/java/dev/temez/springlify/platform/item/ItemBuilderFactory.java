package dev.temez.springlify.platform.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ItemBuilderFactory {

  @NotNull ItemBuilder<ItemBuilder.MaterialItemBuilder> newBuilder(@NotNull Material material);

  @NotNull ItemBuilder<ItemBuilder.ItemStackItemBuilder> newBuilder(@NotNull ItemStack item);

}
