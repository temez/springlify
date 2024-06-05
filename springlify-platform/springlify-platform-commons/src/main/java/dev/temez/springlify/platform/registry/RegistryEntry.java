package dev.temez.springlify.platform.registry;

import org.jetbrains.annotations.NotNull;

public interface RegistryEntry<I> {

  @NotNull I getId();
}