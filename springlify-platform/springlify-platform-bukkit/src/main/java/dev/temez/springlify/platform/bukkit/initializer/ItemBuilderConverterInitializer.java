package dev.temez.springlify.platform.bukkit.initializer;

import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import dev.temez.springlify.platform.commons.text.converter.TextConverter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor to initialize ItemBuilder with a TextConverter.
 *
 * @since 0.5.9.8dev
 */
@Log4j2
@Component
public final class ItemBuilderConverterInitializer implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(
      @NotNull Object bean,
      @NotNull String beanName
  ) throws BeansException {
    if (bean instanceof TextConverter textConverter) {
      log.info("Successfully linked {} to ItemBuilder", textConverter.getClass().getSimpleName());
      ItemBuilder.setConverter(textConverter);
    }
    return bean;
  }
}
