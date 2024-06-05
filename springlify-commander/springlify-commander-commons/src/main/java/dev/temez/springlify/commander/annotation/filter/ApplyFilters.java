package dev.temez.springlify.commander.annotation.filter;

import dev.temez.springlify.commander.command.filter.simple.SimpleCommandFilter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface ApplyFilters {

  @NotNull Class<? extends SimpleCommandFilter>[] value();
}
