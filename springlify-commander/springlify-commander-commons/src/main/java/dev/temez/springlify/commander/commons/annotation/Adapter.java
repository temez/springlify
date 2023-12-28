package dev.temez.springlify.commander.commons.annotation;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Adapter {

  @NotNull Class<? extends ArgumentAdapter<?>> value();
}
