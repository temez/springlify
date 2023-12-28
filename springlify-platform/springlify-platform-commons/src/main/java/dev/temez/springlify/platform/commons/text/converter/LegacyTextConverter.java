package dev.temez.springlify.platform.commons.text.converter;

import jakarta.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;


/**
 * The {@code LegacyTextConverter} class is an implementation of the {@code TextConverter} interface
 * that uses the LegacyComponentSerializer to parse and format text using legacy formatting codes.
 *
 * @since 0.5.9.8dev
 */
@Log4j2
@Service
@SuppressWarnings("all")
@ConditionalOnExpression(
    "'${springlify.platform.text-converter}'.equals('LEGACY') || '${springlify.platform.text-converter}' == null"
)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class LegacyTextConverter implements TextConverter {

  @NotNull
  LegacyComponentSerializer serializer = LegacyComponentSerializer.legacyAmpersand();

  @PostConstruct
  private void initialize() {
    log.info("Using LegacyTextConverter as TextConverter");
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
    return serializer.deserialize("&r")
        .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
  }
}
