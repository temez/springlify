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

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InitializingItemBuilderFactory implements ItemBuilderFactory {

  @NotNull
  TextConverter textConverter;

  @PostConstruct
  private void initialize() {
    ItemBuilder.setConverter(textConverter);
    log.info("Using {} in ItemBuilder", textConverter.getClass().getSimpleName());
  }

  @Override
  public @NotNull ItemBuilder.MaterialItemBuilder newBuilder(@NotNull Material material) {
    return ItemBuilder.fromMaterial(material);
  }

  @Override
  public @NotNull ItemBuilder.ItemStackItemBuilder newBuilder(@NotNull ItemStack item) {
    return ItemBuilder.fromItem(item);
  }
}
