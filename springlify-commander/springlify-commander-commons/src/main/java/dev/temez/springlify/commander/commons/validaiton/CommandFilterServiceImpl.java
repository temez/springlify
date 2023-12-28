package dev.temez.springlify.commander.commons.validaiton;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.registry.CommandFilterRegistry;
import dev.temez.springlify.commander.commons.validaiton.registry.global.GlobalFilterRegistry;
import java.lang.annotation.Annotation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling command filters and validation.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandFilterServiceImpl implements CommandFilterService {

  @NotNull
  CommandFilterRegistry filterRegistry;

  @NotNull
  GlobalFilterRegistry globalFilterRegistry;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAccessible(@NotNull Sender<?> sender, @NotNull RegisteredCommand command) {
    try {
      validate(command, sender);
      return true;
    } catch (ValidationException ignored) {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>Applies global and command-specific filters for validation.</p>
   */
  @Override
  public void validate(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ValidationException {
    applyGlobalFilters(command, sender);
    applyCommandFilters(command, sender);
  }

  private void applyGlobalFilters(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ValidationException {
    globalFilterRegistry
        .getRegisteredFilters()
        .forEach(globalFilter -> globalFilter.filter(command, sender));
  }

  private void applyCommandFilters(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ValidationException {
    command.getValidationContext()
        .getSimpleCommandFilters()
        .forEach(commandFilter -> commandFilter.filter(command, sender));

    command.getValidationContext()
        .getFilterAnnotations()
        .forEach(annotation -> applyFilter(annotation, sender));
  }

  @SuppressWarnings("unchecked")
  private <T extends Annotation> void applyFilter(
      @NotNull T filterAnnotation,
      @NotNull Sender<?> sender
  ) throws ValidationException {
    Class<T> filterAnnotationClass = (Class<T>) filterAnnotation.annotationType();
    CommandFilter<T> filter = filterRegistry.get(filterAnnotationClass);
    filter.filter(sender, filterAnnotation);
  }
}
