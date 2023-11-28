package dev.temez.springlify.platform.velocity.iniitalizer.command;

import com.velocitypowered.api.command.SimpleCommand;
import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * {@code VelocityCommandInitializer} is a Spring BeanPostProcessor responsible for initializing
 * and registering Velocity commands annotated with {@link VelocityCommand}.
 * It scans Spring-managed beans and registers any valid command executor instances with
 * Velocity's CommandManager.
 *
 * <p>This class performs the following tasks:
 * - Detects beans annotated with {@link VelocityCommand}.
 * - Ensures that annotated beans implement the {@link SimpleCommand} interface.
 * - Registers valid command executors with Velocity's CommandManager.
 * - Logs warning messages for beans implementing the {@link SimpleCommand} interface
 * but not annotated with {@link VelocityCommand}.
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VelocityCommandInitializer implements BeanPostProcessor {

  SpringlifyPlugin plugin;

  @Lazy
  public VelocityCommandInitializer(SpringlifyPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Retrieves a SimpleCommand from the specified bean,
   * ensuring it is annotated with {@link VelocityCommand}.
   *
   * @param bean The bean to check for command annotation and type.
   * @return The SimpleCommand instance if valid, or null if not.
   * @throws RuntimeException If the bean's type does not implement SimpleCommand when annotated.
   */
  @Nullable
  private SimpleCommand getCommandExecutor(@NotNull Object bean) throws RuntimeException {
    if (bean.getClass().isAnnotationPresent(VelocityCommand.class)) {
      if (bean instanceof SimpleCommand commandExecutor) {
        return commandExecutor;
      }
      throw new RuntimeException(
          bean.getClass().getSimpleName()
              + " must implement CommandExecutor"
      );
    }
    if (bean instanceof SimpleCommand
        && !bean.getClass().isAnnotationPresent(VelocityCommand.class)) {
      log.warn(
          bean.getClass().getSimpleName()
              + " implements CommandExecutor but not annotated with @SpigotCommand,"
              + " so it wouldn't be registered"
      );
    }
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName)
      throws BeansException {
    SimpleCommand commandExecutor = getCommandExecutor(bean);
    if (commandExecutor == null) {
      return bean;
    }
    VelocityCommand commandAnnotation =
        commandExecutor.getClass().getAnnotation(VelocityCommand.class);
    plugin.getServerPlatformAdapter().registerCommandExecutor(
        commandAnnotation.command(),
        commandExecutor,
        commandAnnotation.alias()
    );
    return bean;
  }
}