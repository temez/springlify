package dev.temez.springlify.platform.commons.text.converter;

import java.util.Collection;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code TextConverter} interface defines methods for converting strings
 * into {@code Component}s for use in the Adventure library.
 *
 * @since 0.5.9.8dev
 */
public interface TextConverter {

  /**
   * Parses a single line of text into an Adventure {@code Component}.
   *
   * @param line The string to parse.
   * @return The corresponding Adventure {@code Component}.
   */
  @NotNull Component parse(@NotNull String line);

  /**
   * Parses a collection of lines into a list of Adventure {@code Component}s.
   *
   * @param lines The collection of strings to parse.
   * @return The list of corresponding Adventure {@code Component}s.
   */
  @NotNull List<Component> parse(@NotNull Collection<String> lines);

  /**
   * Gets the Adventure {@code Component} representing a reset, which
   * clears any previous formatting.
   *
   * @return The reset Adventure {@code Component}.
   */
  @NotNull Component getResetComponent();
}
