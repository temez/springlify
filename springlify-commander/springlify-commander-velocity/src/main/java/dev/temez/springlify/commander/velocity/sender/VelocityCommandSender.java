package dev.temez.springlify.commander.velocity.sender;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import dev.temez.springlify.commander.commons.sender.Sender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class VelocityCommandSender implements Sender<CommandSource> {

  @NotNull CommandSource platformSender;

  @Override
  public boolean isConsoleSender() {
    return platformSender instanceof ConsoleCommandSource;
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
