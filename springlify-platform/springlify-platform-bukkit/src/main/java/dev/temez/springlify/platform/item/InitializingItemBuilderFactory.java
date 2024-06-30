package dev.temez.springlify.platform.item;

import dev.temez.springlify.platform.text.converter.TextConverter;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Component for initializing item builders with a text converter.
 *
 * <p>This component implements the {@link ItemBuilderFactory} interface and initializes
 * item builders with a specified text converter during initialization.</p>
 *
 * @see ItemBuilderFactory
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InitializingItemBuilderFactory implements ItemBuilderFactory {

  /**
   * The text converter used by item builders.
   */
  @NotNull
  TextConverter textConverter;

  /**
   * Initializes the item builder factory by setting the text converter.
   */
  @PostConstruct
  private void initialize() {
    ItemBuilder.setTextConverter(textConverter);
    log.debug("Using {} in ItemBuilder", textConverter.getClass().getSimpleName());
  }

  /**
   * Creates a new item builder for the specified material.
   *
   * @param material the material of the item
   * @return the new item builder
   */
  @Override
  public @NotNull ItemBuilder.MaterialItemBuilder newBuilder(@NotNull Material material) {
    return ItemBuilder.fromMaterial(material);
  }

  /**
   * Creates a new item builder based on the specified ItemStack.
   *
   * @param item the ItemStack to base the item builder on
   * @return the new item builder
   */
  @Override
  public @NotNull ItemBuilder.ItemStackItemBuilder newBuilder(@NotNull ItemStack item) {
    return ItemBuilder.fromItem(item);
  }
}

