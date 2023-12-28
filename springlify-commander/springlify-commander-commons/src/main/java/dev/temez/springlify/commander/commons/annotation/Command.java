package dev.temez.springlify.commander.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

  @NotNull CommandType type() default CommandType.SHARED;

  @NotNull String name();

  @NotNull String description();

  @NotNull String[] alias() default {};

  enum CommandType {

    INGAME,
    CONSOLE,
    SHARED
  }
}


