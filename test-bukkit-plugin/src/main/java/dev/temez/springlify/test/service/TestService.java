package dev.temez.springlify.test.service;

import dev.temez.springlify.platform.bukkit.service.localization.LocalizationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class TestService implements Listener {

  @NotNull LocalizationService localizationService;

  @EventHandler
  private void onJoin(@NotNull PlayerJoinEvent event) {
    Player player = event.getPlayer();
    player.sendMessage(
        localizationService.getLocalizedMessage(player, "system.join-message")
    );
    player.getInventory().addItem(
        localizationService.getLocalizedItemStack(player, "test-skull", "<worth>", "&c3 банки пива")
    );
  }
}
