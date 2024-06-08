package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.annotation.BukkitCommand;
import dev.temez.springlify.starter.server.ServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanPostProcessor;

import static org.mockito.Mockito.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class CommandInitializerTest {

  ServerPlatformAdapter serverPlatformAdapter = mock(ServerPlatformAdapter.class);

  BeanPostProcessor eventHandlerInitializer = new CommandHandlerInitializer(serverPlatformAdapter);

  @Test
  void givenClassWithoutAnnotation_whenPostProcessAfterInitialization_thenShouldNotBeRegistered() {
    NonAnnotatedCommandExecutor executor = new NonAnnotatedCommandExecutor();
    eventHandlerInitializer.postProcessAfterInitialization(executor, "test");

    verify(serverPlatformAdapter, times(0)).registerCommandExecutor(eq("test"), any());
  }


  @Test
  void givenAnnotatedClass_whenPostProcessAfterInitialization_thenShouldNotBeRegistered() {
    AnnotatedClass executor = new AnnotatedClass();
    eventHandlerInitializer.postProcessAfterInitialization(executor, "test");

    verify(serverPlatformAdapter, times(0)).registerCommandExecutor(eq("test"), any());
  }

  @Test
  void givenAnnotatedCommandExecutor_whenPostProcessAfterInitialization_thenShouldBeRegistered() {
    AnnotatedCommandExecutor executor = new AnnotatedCommandExecutor();

    eventHandlerInitializer.postProcessAfterInitialization(executor, "test");

    verify(serverPlatformAdapter, times(1)).registerCommandExecutor(eq("test"), any());
  }


  private static class NonAnnotatedCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      return false;
    }
  }

  private static class AnnotatedClass {

  }

  @BukkitCommand(command = "test")
  private static class AnnotatedCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      return false;
    }
  }

}