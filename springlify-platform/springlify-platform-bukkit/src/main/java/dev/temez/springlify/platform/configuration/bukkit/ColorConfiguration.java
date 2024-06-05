package dev.temez.springlify.platform.configuration.bukkit;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class ColorConfiguration {

  int red;

  int green;

  int blue;
}
