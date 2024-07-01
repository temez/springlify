package dev.temez.springlify.platform.initializer;

import dev.temez.springlify.platform.annotation.VelocityEventListener;
import dev.temez.springlify.platform.server.ServerPlatformAdapter;
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
 * {@link VelocityEventListener} when Spring beans are initialized and destroyed.
 *
 * <p>This class performs the following tasks:
 * - Detects beans annotated with {@link VelocityEventListener}.
 * - Registers event listeners with Velocity's event manager upon bean initialization.
 * - Unregisters event listeners from Velocity's event manager when beans are destroyed.
 * - Logs debug messages when registering and unregistering event listeners.
 *
 * @since 0.7.1.0-SNAPSHOT
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EventListenerInitializer implements DestructionAwareBeanPostProcessor {

  @NotNull
  ServerPlatformAdapter serverPlatformAdapter;

  @Lazy
  public EventListenerInitializer(@NotNull ServerPlatformAdapter serverPlatformAdapter) {
    this.serverPlatformAdapter = serverPlatformAdapter;
  }

  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) {
    if (bean.getClass().isAnnotationPresent(VelocityEventListener.class)) {
      serverPlatformAdapter.registerEventListener(bean);
    }
    return bean;
  }

  @Override
  public void postProcessBeforeDestruction(@NotNull Object bean, @NotNull String beanName) {
    if (bean.getClass().isAnnotationPresent(VelocityEventListener.class)) {
      serverPlatformAdapter.unregisterEventListener(bean);
      log.debug("Unregistered {} from event manager.", bean.getClass().getSimpleName());
    }
  }
}