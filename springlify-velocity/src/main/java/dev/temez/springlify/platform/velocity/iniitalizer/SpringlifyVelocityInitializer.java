package dev.temez.springlify.platform.velocity.iniitalizer;

import dev.temez.springlify.commons.initializer.AbstractSpringlifyInitializer;
import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import dev.temez.springlify.platform.velocity.iniitalizer.command.VelocityCommandInitializer;
import dev.temez.springlify.platform.velocity.iniitalizer.event.EventHandlerInitializer;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * The {@code SpringlifyVelocityInitializer} class extends {@link AbstractSpringlifyInitializer}
 * and provides Velocity-specific initialization for a Spring application context in
 * a Springlify plugin.
 */
public class SpringlifyVelocityInitializer extends AbstractSpringlifyInitializer {

  /**
   * Gets the list of bean post processors for the Velocity-specific initialization.
   * This includes default processors for Velocity command and event handling, as well as
   * any additional processors provided by the plugin.
   *
   * @param plugin The Springlify plugin for which post processors are needed.
   * @return A list of bean post processor classes.
   */
  @Override
  public @NotNull List<Class<? extends BeanPostProcessor>> getPostProcessors(
      @NotNull SpringlifyPlugin plugin
  ) {
    List<Class<? extends BeanPostProcessor>> postProcessorClasses = new ArrayList<>();

    // Add default processors for Velocity command and event handling
    postProcessorClasses.add(VelocityCommandInitializer.class);
    postProcessorClasses.add(EventHandlerInitializer.class);

    // Add any additional processors provided by the plugin
    postProcessorClasses.addAll(plugin.getPostProcessors());

    return postProcessorClasses;
  }
}
