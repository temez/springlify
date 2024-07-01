package dev.temez.springlify.platform.text.formatter;

import dev.temez.springlify.platform.exception.FormattingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TextFormatterImpl implements TextFormatter {

  @Override
  public @NotNull String format(@NotNull String line, Object @NotNull ... replacers) throws FormattingException {
    return format(line, parseReplacers(replacers));
  }

  @Override
  public @NotNull String format(@NotNull String line, @NotNull List<Replacer> replacers) {
    for (Replacer replacer : replacers) {
      line = replacer.apply(line);
    }
    return line;
  }

  @Override
  public @NotNull List<String> format(@NotNull Collection<String> lines, Object @NotNull ... replacers) throws FormattingException {
    return format(lines, parseReplacers(replacers));
  }

  @Override
  public @NotNull List<String> format(@NotNull Collection<String> lines, @NotNull List<Replacer> replacers) {
    return lines.stream()
        .map(line -> format(line, replacers))
        .toList();
  }

  private @NotNull @Unmodifiable List<Replacer> parseReplacers(Object @NotNull [] replacers) throws FormattingException {
    if (replacers.length % 2 != 0) {
      throw new FormattingException("Replacers array must have an even number length!");
    }
    List<Replacer> result = new ArrayList<>();
    for (int i = 0; i < replacers.length; i += 2) {
      result.add(Replacer.of(replacers[i], replacers[i + 1]));
    }
    return List.copyOf(result);
  }
}
