package dev.temez.springlify.platform.text.converter;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Log4j2
@Service
@ConditionalOnExpression(
    "'${springlify.platform.text-converter-mode}'.equalsIgnoreCase('mini_message')"
)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class MiniMessageTextConverter implements TextConverter {

  @NotNull
  MiniMessage serializer = MiniMessage.miniMessage();

  @PostConstruct
  private void initialize() {
    log.info("Using MiniMessageTextConverter as TextConverter");
  }

  @Override
  public @NotNull Component parse(@NotNull String line) {
    return serializer.deserialize(line);
  }

  @Override
  public @NotNull List<Component> parse(@NotNull Collection<String> lines) {
    return lines.stream()
        .map(this::parse)
        .toList();
  }

  @Override
  public @NotNull Component getResetComponent() {
    return serializer.deserialize("<reset>")
        .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
  }
}

