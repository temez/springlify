package dev.temez.springlify.platform.text.formatter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Replacer {

  @NotNull
  String pattern;

  @NotNull
  String content;

  private Replacer(@NotNull Object pattern, @NotNull Object content) {
    this.pattern = pattern.toString();
    this.content = content.toString();
  }

  public static @NotNull Replacer of(@NotNull Object pattern, @NotNull Object content) {
    return new Replacer(pattern, content);
  }

  @NotNull String apply(@NotNull String string) {
    return string.replaceAll(pattern, content);
  }
}
