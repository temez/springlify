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

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandFilterServiceImpl implements CommandFilterService {

  @NotNull CommandFilterRegistry filterRegistry;

  @NotNull GlobalFilterRegistry globalFilterRegistry;

  @Override
  public boolean isAccessible(@NotNull Sender<?> sender, @NotNull RegisteredCommand command) {
    try {
      validate(command, sender);
    } catch (ValidationException ignored) {
      return false;
    }
    return true;
  }

  @Override
  public void validate(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ValidationException {
    globalFilterRegistry
        .getRegisteredFilters()
        .forEach(scf -> scf.filter(command, sender));
    command.getValidationContext()
        .getSimpleCommandFilters()
        .forEach(scf -> scf.filter(command, sender));
    command.getValidationContext()
        .getFilterAnnotations()
        .forEach(annotation -> doFilter(annotation, sender));
  }

  @SuppressWarnings("unchecked")
  private <T extends Annotation> void doFilter(
      @NotNull T filterAnnotation,
      @NotNull Sender<?> sender
  ) throws ValidationException {
    Class<T> filterAnnotationClass = (Class<T>) filterAnnotation.annotationType();
    CommandFilter<T> filter = filterRegistry.get(filterAnnotationClass);
    filter.filter(sender, filterAnnotation);
  }
}
