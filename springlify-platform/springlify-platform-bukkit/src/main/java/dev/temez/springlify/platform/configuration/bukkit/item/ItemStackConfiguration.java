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

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class ItemStackConfiguration {

  @NotNull
  Material material;

  int amount = 1;

  @Nullable
  @NestedConfigurationProperty
  ItemMetaConfiguration itemMeta;
}
