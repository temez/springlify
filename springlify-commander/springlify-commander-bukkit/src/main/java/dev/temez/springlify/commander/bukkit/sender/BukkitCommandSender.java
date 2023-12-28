package dev.temez.springlify.commander.bukkit.sender;

import dev.temez.springlify.commander.commons.sender.Sender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandSender implements Sender<CommandSender> {

  @NotNull CommandSender platformSender;

  @Override
  public boolean isConsoleSender() {
    return !(platformSender instanceof Player);
  }

  @Override
  public boolean hasPermission(@NotNull String permission) {
    return platformSender.hasPermission(permission);
  }

  @Override
  public void sendMessage(@NotNull Component component) {
    platformSender.sendMessage(component);
  }
}
