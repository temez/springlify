package dev.temez.springlify.starter.commons.event.impl;

import org.springframework.context.ApplicationEvent;

/**
 * The {@code ContextPreShutdownEvent} class represents an event indicating that the Spring
 * application
 * context is about to be shut down. This event is triggered before the context is closed, allowing
 * components to perform pre-shutdown tasks.
 *
 * <p>Instances of this class are typically created and published by the Spring framework during the
 * shutdown process. Components interested in performing actions before the context is shut down
 * can listen for and handle instances of this event.</p>
 *
 * @since 0.5.9.6dev
 */
public final class ContextPreShutdownEvent extends ApplicationEvent {

  /**
   * Constructs a new {@code ContextPreShutdownEvent} with the specified source.
   *
   * @param source the object on which the event initially occurred or with which the event
   *               is associated
   * @see ApplicationEvent#ApplicationEvent(Object)
   */
  public ContextPreShutdownEvent(Object source) {
    super(source);
  }
}
