package dev.temez.springlify.commander.annotation.parameter;

import dev.temez.springlify.commander.argument.completer.ArgumentCompleter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Completer {

  @NotNull Class<? extends ArgumentCompleter> value();
}
