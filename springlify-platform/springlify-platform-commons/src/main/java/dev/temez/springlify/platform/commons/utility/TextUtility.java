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
import org.jetbrains.annotations.Unmodifiable;

@UtilityClass
public class TextUtility {

  public @NotNull String formatDouble(double value) {
    return new DecimalFormat("0.0").format(value)
        .replace(',', '.');
  }

  public @NotNull String applyReplacers(@NotNull String string, Object @NotNull ... replacers) {
    for (Replacer replacer : parseReplacers(replacers)) {
      string = replacer.apply(string);
    }
    return string;
  }

  public @NotNull List<String> applyReplacers(@NotNull Collection<String> strings,
                                              Object @NotNull ... replacers) {
    List<String> result = new ArrayList<>();
    strings.forEach(s -> result.add(applyReplacers(s, replacers)));
    return result;
  }

  private @NotNull @Unmodifiable List<Replacer> parseReplacers(
      Object @NotNull ... replacers
  ) throws RuntimeException {
    if (replacers.length % 2 != 0) {
      throw new RuntimeException(
          "Replacers array must have an even number length!"
      );
    }
    List<Replacer> result = new ArrayList<>();
    for (int i = 0; i < replacers.length; i += 2) {
      result.add(new Replacer(replacers[i], replacers[i + 1]));
    }
    return List.copyOf(result);
  }

  @Getter
  @RequiredArgsConstructor
  @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
  public static class Replacer {

    @NotNull
    String pattern;

    @NotNull
    String content;

    public Replacer(@NotNull Object pattern, @NotNull Object content) {
      this.pattern = pattern.toString();
      this.content = content.toString();
    }

    @NotNull String apply(@NotNull String string) {
      return string.replace(pattern, content);
    }
  }

}
