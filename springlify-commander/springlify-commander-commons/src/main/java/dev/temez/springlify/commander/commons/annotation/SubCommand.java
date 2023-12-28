package dev.temez.springlify.commander.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {

  @NotNull String name();

  @NotNull String description();

  @NotNull Command.CommandType type() default Command.CommandType.SHARED;
}
