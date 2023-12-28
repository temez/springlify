package dev.temez.springlify.commander.commons.validaiton.factory;

import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public interface CommandFilterFactory {

  @NotNull List<Annotation> getFilterAnnotations(@NotNull Method commandMethod);

  @NotNull @Unmodifiable List<? extends SimpleCommandFilter> getSimpleFilters(
      @NotNull Method commandMethod);

}
