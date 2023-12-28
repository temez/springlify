package dev.temez.springlify.platform.bukkit.configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Skin} class represents a player skin with texture and signature.
 * It is commonly used in applications or systems that require handling player skin information.
 *
 * <p>The skin is composed of two main components: the texture and the signature.
 * The texture is a base64-encoded string representing the player's skin texture, and the signature
 * is a unique identifier associated with the texture.</p>
 *
 * <p>Instances of this class are typically used to store and retrieve player skin information.</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Skin {

  /**
   * The base64-encoded string representing the player's skin texture.
   */
  @NotNull
  String texture;

  /**
   * The unique identifier associated with the player's skin texture.
   */
  @NotNull
  String signature;
}