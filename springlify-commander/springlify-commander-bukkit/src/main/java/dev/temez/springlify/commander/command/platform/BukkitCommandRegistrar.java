package dev.temez.springlify.commander.command.platform;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * A class responsible for registering Bukkit commands.
 * This class implements the {@link PlatformCommandRegistrar} interface specifically for {@link CommanderBukkitCommand}.
 * It registers the command with the Bukkit {@link CommandMap}.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandRegistrar implements PlatformCommandRegistrar<CommanderBukkitCommand> {

  /**
   * Registers a Bukkit command with the Bukkit command map.
   *
   * @param platformCommand the Bukkit command to register
   */
  @Override
  public void register(@NotNull CommanderBukkitCommand platformCommand) {
    CommandMap commandMap = Bukkit.getCommandMap();
    commandMap.register("commander", platformCommand);
  }
}
