package dev.temez.springlify.example.service;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.discoverer.CommandDiscoverer;
import dev.temez.springlify.commander.command.platform.PlatformCommand;
import dev.temez.springlify.commander.command.platform.PlatformCommandFactory;
import dev.temez.springlify.commander.command.platform.PlatformCommandRegistrar;
import dev.temez.springlify.example.command.ExampleCommand;
import dev.temez.springlify.example.configuration.ExampleConfiguration;
import dev.temez.springlify.platform.configuration.mapper.ConfigurationMapperImpl;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SimpleService implements Listener {

  @NotNull
  PlatformCommandFactory platformCommandFactory;

  @NotNull
  PlatformCommandRegistrar platformCommandRegistrar;

  @NotNull
  CommandDiscoverer<Class<?>> commandDiscoverer;

  @NotNull
  ExampleConfiguration exampleConfiguration;

  @NotNull
  ConfigurationMapperImpl configurationMapper;

  @PostConstruct
  private void initialize() {
    System.out.println(exampleConfiguration.getItemStackConfiguration());
    System.out.println(configurationMapper.map(exampleConfiguration.getItemStackConfiguration(), ItemStack.class));

    Command command = commandDiscoverer.discover(ExampleCommand.class);
    PlatformCommand platformCommand = platformCommandFactory.create(command);
    platformCommandRegistrar.register(platformCommand);
  }

  @EventHandler
  void onEvent(@NotNull PlayerJoinEvent event) {
    event.getPlayer().sendMessage("ИДИ НАХУЙ ПИДОР");
  }
}
