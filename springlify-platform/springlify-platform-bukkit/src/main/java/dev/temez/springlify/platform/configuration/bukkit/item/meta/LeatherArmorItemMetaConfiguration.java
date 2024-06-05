package dev.temez.springlify.platform.configuration.bukkit.item.meta;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class LeatherArmorItemMetaConfiguration {

  @NotNull
  @NestedConfigurationProperty
  ColorConfiguration color;

}