package dev.temez.springlify.commander.commons.initializer;

import dev.temez.springlify.commander.commons.annotation.Command;
import dev.temez.springlify.commander.commons.command.registry.CommandRegistry;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Bean post-processor for initializing and registering command executors annotated
 * with {@code @Command}.
 *
 * @since 0.5.8.9dev
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandInitializer implements BeanPostProcessor {

  @NotNull
  CommandRegistry commandRegistry;

  /**
   * Lazy-initialized constructor for CommandInitializer.
   *
   * @param commandRegistry The registry for storing command executors.
   */
  @Lazy
  @Autowired
  public CommandInitializer(@NotNull CommandRegistry commandRegistry) {
    this.commandRegistry = commandRegistry;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Object postProcessAfterInitialization(@NotNull Object bean,
                                                        @NotNull String beanName) {
    if (bean.getClass().isAnnotationPresent(Command.class)) {
      log.info("Registering {} as command executor.", bean.getClass().getSimpleName());
      commandRegistry.register(bean);
    }
    return bean;
  }
}
