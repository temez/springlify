package dev.temez.springlify.commander.command.filter.global;

import dev.temez.springlify.commander.command.filter.simple.SimpleCommandFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface GlobalFilterResolver {

  @NotNull @Unmodifiable List<SimpleCommandFilter> getFilters();

}
