package dev.temez.springlify.platform.text;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class Replacer implements Function<String, String> {

  @NotNull
  String pattern;

  @NotNull
  String content;

  private Replacer(@NotNull Object pattern, @NotNull Object content) {
    this.pattern = pattern.toString();
    this.content = content.toString();
  }

  @Contract("_,_ -> new")
  public static @NotNull Replacer of(@NotNull Object pattern, @NotNull Object content) {
    return new Replacer(pattern, content);
  }

  @Override
  public @NotNull String apply(@NotNull String string) {
    return string.replace(pattern, content);
  }
}
