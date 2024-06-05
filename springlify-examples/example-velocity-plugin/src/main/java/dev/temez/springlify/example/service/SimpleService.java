package dev.temez.springlify.example.service;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

  @Subscribe
  void onEvent(@NotNull ServerConnectedEvent event) {
    event.getPlayer().sendMessage(Component.text("ИДИ НАХУЙ ПИДОР"));
  }
}
