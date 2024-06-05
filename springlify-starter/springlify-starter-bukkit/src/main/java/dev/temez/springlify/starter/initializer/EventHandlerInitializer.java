package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class EventHandlerInitializer implements DestructionAwareBeanPostProcessor {

  @NotNull
  SpringlifyPlugin plugin;

  @Lazy
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public EventHandlerInitializer(@NotNull SpringlifyPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
    if (bean instanceof Listener listener) {
      plugin.getServerPlatformAdapter().registerEventListener(listener);
    }
    return bean;
  }

  @Override
  public void postProcessBeforeDestruction(@NotNull Object bean, @NotNull String beanName) {
    if (bean instanceof Listener listener) {
      plugin.getServerPlatformAdapter().unregisterEventListener(listener);
    }
  }
}

