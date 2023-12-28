package dev.temez.springlify.commander.velocity.validation.annotation;

import dev.temez.springlify.commander.commons.annotation.validation.ValidateWith;
import dev.temez.springlify.commander.velocity.validation.RestrictServersFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;

@Order(0)
@ValidateWith(RestrictServersFilter.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestrictServers {

  @NotNull String[] value();
}
