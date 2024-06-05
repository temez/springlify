package dev.temez.springlify.platform.configuration.bukkit;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class SkinTextureConfiguration {

  @NotNull
  String texture;

  @NotNull
  String signature;
}