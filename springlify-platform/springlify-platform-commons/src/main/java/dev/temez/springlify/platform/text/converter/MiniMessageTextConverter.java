package dev.temez.springlify.platform.text.converter;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
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
import java.util.stream.Collectors;

/**
 * Implementation of {@link TextConverter} using MiniMessage for parsing text into adventure components.
 *
 * <p>This class parses text lines using MiniMessage syntax into adventure components.
 * It is conditionally enabled based on the configuration property {@code springlify.platform.text-converter-mode}
 * being set to 'mini_message'.</p>
 *
 * @see TextConverter
 * @see MiniMessage
 * @since 0.7.0.0-RC1
 */
@Log4j2
@Service
@ConditionalOnExpression(
    "'${springlify.platform.text-converter-mode}'.equalsIgnoreCase('mini_message')"
)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MiniMessageTextConverter implements TextConverter {

  @NotNull
  MiniMessage serializer;

  /**
   * Logs a debug message upon initialization.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Using MiniMessageTextConverter as TextConverter");
  }

  /**
   * Parses a single text line into an adventure component using MiniMessage.
   *
   * @param line the text line to parse
   * @return the adventure component representing the parsed line
   */
  @Override
  public @NotNull Component parse(@NotNull String line) {
    return serializer.deserialize(line);
  }

  /**
   * Parses a collection of text lines into a list of adventure components using MiniMessage.
   *
   * @param lines the collection of text lines to parse
   * @return the list of adventure components representing the parsed lines
   */
  @Override
  public @NotNull List<Component> parse(@NotNull Collection<String> lines) {
    return lines.stream()
        .map(this::parse)
        .collect(Collectors.toList());
  }

  /**
   * Retrieves the reset component for formatting using MiniMessage.
   *
   * @return the reset component
   */
  @Override
  public @NotNull Component getResetComponent() {
    return serializer.deserialize("<reset>")
        .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
  }

  @Override
  public @NotNull String revert(@NotNull Component component) {
    return serializer.serialize(component);
  }
}
