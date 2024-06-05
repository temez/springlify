package dev.temez.springlify.commander.command.filter.global;

import dev.temez.springlify.commander.annotation.context.Global;
import dev.temez.springlify.commander.command.filter.simple.SimpleCommandFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final
class GlobalFilterResolverImpl implements GlobalFilterResolver {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  public @NotNull @Unmodifiable List<SimpleCommandFilter> getFilters() {
    return applicationContext.getBeansOfType(SimpleCommandFilter.class)
        .values()
        .stream()
        .filter(filter -> filter.getClass().isAnnotationPresent(Global.class))
        .toList();
  }
}
