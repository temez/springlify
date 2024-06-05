package dev.temez.springlify.commander.annotation.filter;

import dev.temez.springlify.commander.command.filter.CommandFilter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface ValidateWith {

  @NotNull Class<? extends CommandFilter<?>> value();
}
