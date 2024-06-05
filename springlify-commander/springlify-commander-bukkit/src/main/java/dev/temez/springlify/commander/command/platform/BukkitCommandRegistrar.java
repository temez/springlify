package dev.temez.springlify.commander.command.platform;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandRegistrar implements PlatformCommandRegistrar {

  @Override
  public void register(@NotNull PlatformCommand platformCommand) {
    platformCommand.register();
  }
}