package dev.temez.springlify.platform.bukkit.configuration.item;

import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ConfigurableMeta} interface defines a functional interface for configuring metadata
 * (meta) information of an item using a {@link ItemBuilder.MaterialItemBuilder}.
 *
 * <p>Implementations of this interface are expected to provide a configuration function that
 * accepts a {@link ItemBuilder.MaterialItemBuilder} and returns a modified or decorated
 * {@link ItemBuilder.MaterialItemBuilder} with updated metadata settings.</p>
 *
 * <p>This interface is designed to be used with classes that allow dynamic configuration of item
 * metadata, providing a flexible way to apply additional meta information to items.</p>
 *
 * @since 0.5.9.8dev
 */
public interface ConfigurableMeta<T> {

  @NotNull ItemBuilder.MaterialItemBuilder apply(@NotNull T builder, Object @NotNull ... params);
}
