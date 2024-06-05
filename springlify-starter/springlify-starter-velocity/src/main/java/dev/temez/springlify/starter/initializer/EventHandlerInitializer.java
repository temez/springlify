package dev.temez.springlify.starter.initializer;


import dev.temez.springlify.starter.annotation.VelocityEventHandler;
import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class EventHandlerInitializer implements DestructionAwareBeanPostProcessor {

  SpringlifyPlugin plugin;

  @Lazy
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public EventHandlerInitializer(SpringlifyPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) {
    if (bean.getClass().isAnnotationPresent(VelocityEventHandler.class)) {
      plugin.getServerPlatformAdapter().registerEventListener(bean);
    }
    return bean;
  }

  @Override
  public void postProcessBeforeDestruction(@NotNull Object bean, @NotNull String beanName) {
    if (bean.getClass().isAnnotationPresent(VelocityEventHandler.class)) {
      plugin.getServerPlatformAdapter().unregisterEventListener(bean);
    }
  }
}