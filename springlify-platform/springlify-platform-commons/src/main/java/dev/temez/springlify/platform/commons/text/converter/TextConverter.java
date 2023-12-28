package dev.temez.springlify.platform.commons.text.converter;

import java.util.Collection;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface TextConverter {

  @NotNull Component parse(@NotNull String line);

  @NotNull List<Component> parse(@NotNull Collection<String> lines);

  @NotNull Component resetComponent();

}
