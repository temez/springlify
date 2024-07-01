package dev.temez.springlify.platform.text.formatter;

import dev.temez.springlify.platform.exception.FormattingException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface TextFormatter {

  @NotNull String format(@NotNull String line, Object @NotNull ... replacers) throws FormattingException;

  @NotNull String format(@NotNull String line, @NotNull List<Replacer> replacers);

  @NotNull List<String> format(@NotNull Collection<String> lines, Object @NotNull ... replacers) throws FormattingException;

  @NotNull List<String> format(@NotNull Collection<String> lines, @NotNull List<Replacer> replacers);
}
