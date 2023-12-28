package dev.temez.springlify.commander.commons.initializer;

import dev.temez.springlify.commander.commons.validaiton.CommandFilter;
import dev.temez.springlify.commander.commons.validaiton.registry.CommandFilterRegistry;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Bean post-processor for initializing and registering CommandFilters.
 *
 * @since 0.5.8.9dev
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandFilterInitializer implements BeanPostProcessor {

  @NotNull
  CommandFilterRegistry commandFilterRegistry;

  /**
   * Lazy-initialized constructor for CommandFilterInitializer.
   *
   * @param commandFilterRegistry The registry for storing CommandFilters.
   */
  @Lazy
  public CommandFilterInitializer(@NotNull CommandFilterRegistry commandFilterRegistry) {
    this.commandFilterRegistry = commandFilterRegistry;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Object postProcessAfterInitialization(@NotNull Object bean,
                                                        @NotNull String beanName) {
    if (bean instanceof CommandFilter<?> commandFilter) {
      commandFilterRegistry.register(commandFilter);
      log.info("Registered {} as CommandFilter.", bean.getClass().getSimpleName());
    }
    return bean;
  }
}

