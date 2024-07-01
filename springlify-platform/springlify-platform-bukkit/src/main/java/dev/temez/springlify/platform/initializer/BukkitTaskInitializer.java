package dev.temez.springlify.platform.initializer;

import dev.temez.springlify.platform.task.AbstractTask;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BukkitTaskInitializer implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
    if(bean instanceof AbstractTask task) {
      task.start();
      log.debug("Started {} with taskId {}", task.getClass().getSimpleName(), task.getTaskId());
    }
    return bean;
  }
}
