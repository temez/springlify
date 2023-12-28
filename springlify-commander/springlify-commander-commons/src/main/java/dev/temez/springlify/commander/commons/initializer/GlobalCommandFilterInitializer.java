package dev.temez.springlify.commander.commons.initializer;

import dev.temez.springlify.commander.commons.annotation.context.Global;
import dev.temez.springlify.commander.commons.validaiton.registry.global.GlobalFilterRegistry;
import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class GlobalCommandFilterInitializer implements BeanPostProcessor {

  @NotNull GlobalFilterRegistry globalFilterRegistry;

  @Lazy
  public GlobalCommandFilterInitializer(@NotNull GlobalFilterRegistry globalFilterRegistry) {
    this.globalFilterRegistry = globalFilterRegistry;
  }

  @Override
  public @NotNull Object postProcessAfterInitialization(@NotNull Object bean,
                                                        @NotNull String beanName) {
    if (bean instanceof SimpleCommandFilter commandFilter
        && bean.getClass().isAnnotationPresent(Global.class)) {
      globalFilterRegistry.register(commandFilter);
    }
    return bean;
  }
}
