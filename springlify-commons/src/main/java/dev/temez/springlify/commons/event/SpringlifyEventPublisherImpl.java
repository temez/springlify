package dev.temez.springlify.commons.event;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * The {@code SpringlifyEventPublisherImpl} class is an implementation of the
 * {@code SpringlifyEventPublisher} interface. It serves as a Spring component
 * responsible for publishing Spring application events
 * to the application context.
 *
 * <p>Instances of this class can be injected into other components to facilitate the publication
 * of custom events within the application.</p>
 *
 * @since 0.5.9.6dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SpringlifyEventPublisherImpl implements SpringlifyEventPublisher {

  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void publish(@NotNull ApplicationEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
