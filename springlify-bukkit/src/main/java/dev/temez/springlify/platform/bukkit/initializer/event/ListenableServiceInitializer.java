package dev.temez.springlify.platform.bukkit.initializer.event;

import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * The {@code ListenableServiceInitializer} class is a Spring
 * {@link DestructionAwareBeanPostProcessor} responsible for initializing and cleaning
 * up services that implement the Bukkit {@link Listener} interface.
 * It registers and unregisters them as event listeners with the associated Bukkit plugin.
 */
@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ListenableServiceInitializer implements DestructionAwareBeanPostProcessor {

  /**
   * The Springlify platform plugin associated with the initializer.
   */
  @NotNull
  SpringlifyPlugin plugin;

  @Lazy
  public ListenableServiceInitializer(@NotNull SpringlifyPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Processes a bean after initialization, registering it as a Bukkit event listener if applicable.
   *
   * @param bean     The bean instance.
   * @param beanName The name of the bean.
   * @return The processed bean.
   */
  @Override
  public Object postProcessAfterInitialization(
      @NotNull Object bean,
      @NotNull String beanName
  ) throws BeansException {
    if (bean instanceof Listener listener) {
      plugin.getServerPlatformAdapter().registerListener(listener);
    }
    return bean;
  }

  /**
   * Processes a bean before destruction, unregistering it as a Bukkit event listener if applicable.
   *
   * @param bean     The bean instance.
   * @param beanName The name of the bean.
   */
  @Override
  public void postProcessBeforeDestruction(@NotNull Object bean, @NotNull String beanName) {
    if (bean instanceof Listener listener) {
      plugin.getServerPlatformAdapter().unregisterListener(listener);
    }
  }
}

