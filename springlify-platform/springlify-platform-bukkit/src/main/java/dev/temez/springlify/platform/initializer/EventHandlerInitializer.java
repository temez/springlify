package dev.temez.springlify.platform.initializer;

import dev.temez.springlify.platform.server.ServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Initializes and registers event listeners with the {@link ServerPlatformAdapter}.
 *
 * <p>This class implements the {@link DestructionAwareBeanPostProcessor} interface
 * to handle the registration of {@link Listener} beans after they are initialized
 * and their unregistration before they are destroyed.</p>
 *
 * @see ServerPlatformAdapter
 * @see Listener
 * @since 0.7.0.0-RC1
 */
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class EventHandlerInitializer implements DestructionAwareBeanPostProcessor {

  @NotNull
  ServerPlatformAdapter platformAdapter;

  /**
   * Constructs an {@code EventHandlerInitializer} with the specified {@link ServerPlatformAdapter}.
   *
   * @param platformAdapter the platform adapter used to register and unregister listeners
   */
  @Lazy
  public EventHandlerInitializer(@NotNull ServerPlatformAdapter platformAdapter) {
    this.platformAdapter = platformAdapter;
  }

  /**
   * {@inheritDoc}
   *
   * @param bean     the bean being processed
   * @param beanName the name of the bean
   * @return the processed bean
   * @throws BeansException if any error occurs during processing
   */
  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
    if (bean instanceof Listener listener) {
      platformAdapter.registerEventListener(listener);
    }
    return bean;
  }

  /**
   * {@inheritDoc}
   *
   * @param bean     the bean being processed
   * @param beanName the name of the bean
   */
  @Override
  public void postProcessBeforeDestruction(@NotNull Object bean, @NotNull String beanName) {
    if (bean instanceof Listener listener) {
      platformAdapter.unregisterEventListener(listener);
    }
  }
}
