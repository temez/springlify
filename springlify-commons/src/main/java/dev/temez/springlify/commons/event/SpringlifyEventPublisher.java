package dev.temez.springlify.commons.event;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEvent;

/**
 * The {@code SpringlifyEventPublisher} interface defines a contract for publishing Spring
 * application events. Implementations of this interface allow components to publish events
 * to the Spring application context.
 *
 * <p>Event publishers can be used to broadcast custom events within the application, allowing other
 * components to listen for and react to these events.</p>
 *
 * @since 0.5.9.6dev
 */
public interface SpringlifyEventPublisher {

  /**
   * Publishes the specified Spring application event to the application context.
   *
   * @param event the application event to be published
   */
  void publish(@NotNull ApplicationEvent event);
}