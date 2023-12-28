package dev.temez.springlify.commander.bukkit.command;

import dev.temez.springlify.commander.commons.command.platform.PlatformCommand;
import dev.temez.springlify.commander.commons.command.platform.PlatformCommandRegistrar;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link PlatformCommandRegistrar} for the Bukkit platform.
 *
 * <p>This registrar is responsible for registering {@link PlatformCommand}s
 * on the Bukkit platform.</p>
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandRegistrar implements PlatformCommandRegistrar {

  /**
   * {@inheritDoc}
   */
  @Override
  public void register(@NotNull PlatformCommand platformCommand) {
    platformCommand.register();
  }
}
