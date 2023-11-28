package dev.temez.springlify.platform.velocity.iniitalizer.event;

import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * The {@code ListenableServiceInitializer} class is a Spring DestructionAwareBeanPostProcessor.
 * It automatically registers and unregisters event listeners annotated with
 * {@link VelocityEventHandler} when Spring beans are initialized and destroyed.
 *
 * <p>This class performs the following tasks:
 * - Detects beans annotated with {@link VelocityEventHandler}.
 * - Registers event listeners with Velocity's event manager upon bean initialization.
 * - Unregisters event listeners from Velocity's event manager when beans are destroyed.
 * - Logs debug messages when registering and unregistering event listeners.
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EventHandlerInitializer implements DestructionAwareBeanPostProcessor {

  SpringlifyPlugin plugin;

  @Lazy
  public EventHandlerInitializer(SpringlifyPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) {
    if (bean.getClass().isAnnotationPresent(VelocityEventHandler.class)) {
      plugin.getServerPlatformAdapter().registerListener(bean);
    }
    return bean;
  }

  @Override
  public void postProcessBeforeDestruction(@NotNull Object bean, @NotNull String beanName) {
    if (bean.getClass().isAnnotationPresent(VelocityEventHandler.class)) {
      plugin.getServerPlatformAdapter().unregisterListener(bean);
      log.debug("Unregistered {} from event manager.", bean.getClass().getSimpleName());
    }
  }
}