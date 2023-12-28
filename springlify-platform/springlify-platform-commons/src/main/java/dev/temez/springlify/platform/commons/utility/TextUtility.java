package dev.temez.springlify.platform.commons.utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code TextUtility} class provides utility methods for text formatting and replacement.
 *
 * @since 0.5.9.8dev
 */
@UtilityClass
@SuppressWarnings("unused")
public class TextUtility {

  /**
   * Formats a double value with one decimal place and replaces ',' with '.' if present.
   *
   * @param value The double value to format.
   * @return The formatted string.
   */
  public @NotNull String formatDouble(double value) {
    return new DecimalFormat("0.0").format(value)
        .replace(',', '.');
  }

  /**
   * Applies a list of replacers to the given string.
   *
   * @param string    The string to apply the replacers to.
   * @param replacers An array of replacers where each pair represents the pattern
   *                  and its replacement.
   * @return The modified string.
   */
  public @NotNull String applyReplacers(@NotNull String string, Object @NotNull ... replacers) {
    for (Replacer replacer : parseReplacers(replacers)) {
      string = replacer.apply(string);
    }
    return string;
  }

  /**
   * Applies a list of replacers to each string in the given collection.
   *
   * @param strings   The collection of strings to apply the replacers to.
   * @param replacers An array of replacers where each pair represents
   *                  the pattern and its replacement.
   * @return The list of modified strings.
   */
  public @NotNull List<String> applyReplacers(@NotNull Collection<String> strings,
                                              Object @NotNull ... replacers) {
    List<String> result = new ArrayList<>();
    strings.forEach(s -> result.add(applyReplacers(s, replacers)));
    return result;
  }

  private @NotNull List<Replacer> parseReplacers(Object @NotNull ... replacers)
      throws RuntimeException {
    if (replacers.length % 2 != 0) {
      throw new RuntimeException("Replacers array must have an even number length!");
    }
    List<Replacer> result = new ArrayList<>();
    for (int i = 0; i < replacers.length; i += 2) {
      result.add(new Replacer(replacers[i], replacers[i + 1]));
    }
    return List.copyOf(result);
  }

  /**
   * The {@code Replacer} class represents a replacement operation with a pattern and content.
   */
  @Getter
  @RequiredArgsConstructor
  @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
  public static class Replacer {

    /**
     * The pattern to be replaced.
     */
    @NotNull
    String pattern;

    /**
     * The content to replace the pattern with.
     */
    @NotNull
    String content;

    /**
     * Creates a new instance of the {@code Replacer} class.
     *
     * @param pattern The pattern to be replaced.
     * @param content The content to replace the pattern with.
     */
    public Replacer(@NotNull Object pattern, @NotNull Object content) {
      this.pattern = pattern.toString();
      this.content = content.toString();
    }

    /**
     * Applies the replacement to the given string.
     *
     * @param string The string to be modified.
     * @return The modified string.
     */
    @NotNull String apply(@NotNull String string) {
      return string.replace(pattern, content);
    }
  }
}
