package dev.temez.springlify.commander.commons.command;

import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import java.lang.annotation.Annotation;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandValidationContext {

  @NotNull @Unmodifiable List<Annotation> filterAnnotations;

  @NotNull @Unmodifiable List<? extends SimpleCommandFilter> simpleCommandFilters;
}
