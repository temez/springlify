package dev.temez.springlify.commander.commons.initializer;

import dev.temez.springlify.commander.commons.annotation.context.Global;
import dev.temez.springlify.commander.commons.validaiton.registry.global.GlobalFilterRegistry;
import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Bean post-processor for initializing and registering global CommandFilters
 * annotated with {@code @Global}.
 *
 * @since 0.5.8.9dev
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class GlobalCommandFilterInitializer implements BeanPostProcessor {

  @NotNull
  GlobalFilterRegistry globalFilterRegistry;

  /**
   * Lazy-initialized constructor for GlobalCommandFilterInitializer.
   *
   * @param globalFilterRegistry The registry for storing global CommandFilters.
   */
  @Lazy
  @Autowired
  public GlobalCommandFilterInitializer(@NotNull GlobalFilterRegistry globalFilterRegistry) {
    this.globalFilterRegistry = globalFilterRegistry;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Object postProcessAfterInitialization(@NotNull Object bean,
                                                        @NotNull String beanName) {
    if (bean instanceof SimpleCommandFilter commandFilter
        && bean.getClass().isAnnotationPresent(Global.class)) {
      globalFilterRegistry.register(commandFilter);
      log.info("Registered {} as global CommandFilter.", bean.getClass().getSimpleName());
    }
    return bean;
  }
}
