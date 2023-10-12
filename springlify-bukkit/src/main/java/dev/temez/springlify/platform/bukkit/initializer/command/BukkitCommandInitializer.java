package dev.temez.springlify.platform.bukkit.initializer.command;

import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * The {@code BukkitCommandInitializer} class is a Spring {@link BeanPostProcessor}
 * responsible for initializing Bukkit commands.
 * It scans beans for the {@link BukkitCommand} annotation and registers
 * them as command executors with the Bukkit plugin.
 */
@Log4j2
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BukkitCommandInitializer implements BeanPostProcessor {

  @NotNull
  SpringlifyPlugin plugin;

  @Lazy
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public BukkitCommandInitializer(@NotNull SpringlifyPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Processes a bean after initialization, registering it as a Bukkit command executor
   * if applicable.
   *
   * @param bean     The bean instance.
   * @param beanName The name of the bean.
   * @return The processed bean.
   */
  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) {
    BukkitCommand bukkitCommand = bean.getClass().getAnnotation(BukkitCommand.class);

    if (bukkitCommand == null) {
      if (bean instanceof CommandExecutor && !(bean instanceof JavaPlugin)) {
        log.warn(
            "{} implements CommandExecutor but is not annotated with @BukkitCommand,"
                + " so it won't be registered.",
            bean.getClass().getSimpleName()
        );
      }
      return bean;
    }

    if (bean instanceof CommandExecutor commandExecutor) {
      plugin.getServerPlatformAdapter().registerCommandExecutor(
          bukkitCommand.command(),
          commandExecutor
      );
      return bean;
    }

    log.error(
        "Command executor, annotated with @BukkitCommand {}  must implement "
            + "CommandExecutor.",
        bean.getClass().getSimpleName()
    );
    return bean;
  }
}
