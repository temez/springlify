package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.annotation.BukkitCommand;
import dev.temez.springlify.starter.server.ServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandHandlerInitializer implements BeanPostProcessor {

  @NotNull
  ServerPlatformAdapter serverPlatformAdapter;

  @Lazy
  public CommandHandlerInitializer(@NotNull ServerPlatformAdapter serverPlatformAdapter) {
    this.serverPlatformAdapter = serverPlatformAdapter;
  }

  @Override
  public @NotNull Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
    Class<?> beanClass = bean.getClass();
    if (bean instanceof CommandExecutor executor && !(bean instanceof JavaPlugin)) {
      BukkitCommand commandAnnotation = beanClass.getAnnotation(BukkitCommand.class);
      if (commandAnnotation == null) {
        log.warn("{} implements CommandExecutor but is not annotated with @BukkitCommand, so it won't be registered.", beanClass.getName());
        return bean;
      }
      serverPlatformAdapter.registerCommandExecutor(commandAnnotation.command(), executor);
      return bean;
    }
    if (beanClass.isAnnotationPresent(BukkitCommand.class)) {
      log.warn("Class, annotated with @BukkitCommand {} should implement CommandExecutor.", beanClass.getName());
    }
    return bean;
  }
}
