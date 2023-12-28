package dev.temez.springlify.commander.commons.initializer;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.adapter.ArgumentAdapterFactory;
import dev.temez.springlify.commander.commons.annotation.context.External;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Bean post-processor for initializing and registering ArgumentAdapters.
 *
 * @since 0.5.8.9dev
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ArgumentAdapterInitializer implements BeanPostProcessor {

  @NotNull
  ArgumentAdapterFactory argumentAdapterFactory;

  /**
   * Lazy-initialized constructor for ArgumentAdapterInitializer.
   *
   * @param argumentAdapterFactory The factory for creating ArgumentAdapters.
   */
  @Lazy
  @Autowired
  public ArgumentAdapterInitializer(@NotNull ArgumentAdapterFactory argumentAdapterFactory) {
    this.argumentAdapterFactory = argumentAdapterFactory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object postProcessBeforeInitialization(@NotNull Object bean, @NotNull String beanName) {
    if (bean instanceof ArgumentAdapter<?> argumentAdapter) {
      if (bean.getClass().isAnnotationPresent(External.class)) {
        log.info("Discovered {} as external argument adapter.", bean.getClass().getSimpleName());
        return bean;
      }
      argumentAdapterFactory.register(argumentAdapter);
      log.info("Registered {} as argument adapter.", bean.getClass().getSimpleName());
    }
    return bean;
  }
}
