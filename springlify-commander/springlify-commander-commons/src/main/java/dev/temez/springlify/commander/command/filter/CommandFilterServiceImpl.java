package dev.temez.springlify.commander.command.filter;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.factory.CommandFilterFactory;
import dev.temez.springlify.commander.command.filter.global.GlobalFilterResolver;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.CommandFilterException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandFilterServiceImpl implements CommandFilterService {

  @NotNull
  GlobalFilterResolver globalFilterResolver;

  @NotNull
  CommandFilterFactory commandFilterFactory;

  @Override
  public boolean isAccessible(@NotNull Command command, @NotNull Sender<?> sender) {
    try {
      filter(command, sender);
      return true;
    } catch (CommandFilterException exception) {
      return false;
    }
  }

  @Override
  public void filter(@NotNull Command command, @NotNull Sender<?> sender) throws CommandFilterException {
    globalFilterResolver.getFilters()
        .forEach(commandFilter -> commandFilter.filter(command, sender));

    command.getCommandFilteringMetadata()
        .getSimpleCommandFilters()
        .forEach(commandFilter -> commandFilter.filter(command, sender));

    command.getCommandFilteringMetadata()
        .getFilterAnnotations()
        .forEach(annotation -> applyFilter(annotation, sender));
  }

  @SuppressWarnings("unchecked")
  private <T extends Annotation> void applyFilter(@NotNull T filterAnnotation, @NotNull Sender<?> sender) throws CommandFilterException {
    Class<T> filterAnnotationClass = (Class<T>) filterAnnotation.annotationType();
    CommandFilter<T> filter = commandFilterFactory.getFilter(filterAnnotationClass);
    filter.filter(sender, filterAnnotation);
  }
}
