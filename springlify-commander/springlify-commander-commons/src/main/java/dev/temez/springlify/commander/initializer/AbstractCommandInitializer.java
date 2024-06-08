package dev.temez.springlify.commander.initializer;

import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.discoverer.CommandDiscoverer;
import dev.temez.springlify.commander.command.platform.PlatformCommandFactory;
import dev.temez.springlify.commander.command.platform.PlatformCommandRegistrar;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * An abstract command initializer responsible for initializing and registering platform-specific commands.
 * This class implements the {@link BeanPostProcessor} interface.
 *
 * @param <T> the type of platform-specific command
 * @since 0.7.0.0-RC1
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class AbstractCommandInitializer<T> implements BeanPostProcessor {

  /**
   * The factory for creating platform-specific commands.
   */
  @NotNull
  PlatformCommandFactory<T> commandFactory;

  /**
   * The registrar for registering platform-specific commands.
   */
  @NotNull
  PlatformCommandRegistrar<T> commandRegistrar;

  /**
   * The discoverer for discovering commands.
   */
  @NotNull
  CommandDiscoverer commandDiscoverer;

  /**
   * Processes the bean after initialization, discovering and registering commands.
   *
   * @param bean     the bean being post-processed
   * @param beanName the name of the bean
   * @return the processed bean
   */
  @Override
  public @NotNull Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) {
    Class<?> beanClass = bean.getClass();
    if (beanClass.getDeclaringClass() == null && beanClass.isAnnotationPresent(CommanderCommand.class)) {
      Command command = commandDiscoverer.discover(bean);
      T platformCommand = commandFactory.create(command);
      commandRegistrar.register(platformCommand);
      log.debug("Registered command {} for bean {}", command.getFullName(), beanName);
    }
    return bean;
  }
}
