package dev.temez.springlify.platform.text.converter;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface TextConverter {

  @NotNull Component parse(@NotNull String line);

  @NotNull List<Component> parse(@NotNull Collection<String> lines);

  @NotNull Component getResetComponent();
}
