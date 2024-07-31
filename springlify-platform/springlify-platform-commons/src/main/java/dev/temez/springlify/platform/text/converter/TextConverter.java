package dev.temez.springlify.platform.text.converter;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * Interface for converting text to adventure components.
 *
 * <p>This interface defines methods for parsing text lines into adventure components,
 * as well as providing a reset component for formatting.</p>
 *
 * @see Component
 * @since 0.7.0.0-RC1
 */
public interface TextConverter {

  /**
   * Parses a single text line into an adventure component.
   *
   * @param line the text line to parse
   * @return the adventure component representing the parsed line
   */
  @NotNull
  Component parse(@NotNull String line);

  /**
   * Parses a collection of text lines into a list of adventure components.
   *
   * @param lines the collection of text lines to parse
   * @return the list of adventure components representing the parsed lines
   */
  @NotNull
  List<Component> parse(@NotNull Collection<String> lines);

  /**
   * Retrieves the reset component for formatting.
   *
   * @return the reset component
   */
  @NotNull
  Component getResetComponent();

  @NotNull
  String revert(@NotNull Component component);
}
