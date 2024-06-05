package dev.temez.springlify.platform.configuration.bukkit;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;


@Setter
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class LocationConfiguration {

  @NotNull
  String world;

  double x;

  double y;

  double z;

  float yaw;

  float pitch;

}

