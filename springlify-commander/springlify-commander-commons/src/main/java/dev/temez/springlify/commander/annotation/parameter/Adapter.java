package dev.temez.springlify.commander.annotation.parameter;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Adapter {

  @NotNull Class<? extends ArgumentAdapter<?>> value();
}
