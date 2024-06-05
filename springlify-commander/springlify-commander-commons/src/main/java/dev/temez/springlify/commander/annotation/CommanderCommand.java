package dev.temez.springlify.commander.annotation;

import dev.temez.springlify.commander.command.CommandType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CommanderCommand {

  @NotNull CommandType type() default CommandType.SHARED;

  @NotNull String name();

  @NotNull String description();

  @NotNull String[] alias() default {};
}

