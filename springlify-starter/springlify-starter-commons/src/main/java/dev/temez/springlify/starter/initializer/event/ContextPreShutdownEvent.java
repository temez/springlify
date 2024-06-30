package dev.temez.springlify.starter.initializer.event;

import org.springframework.context.ApplicationEvent;

public class ContextPreShutdownEvent extends ApplicationEvent {

  public ContextPreShutdownEvent(Object source) {
    super(source);
  }
}
