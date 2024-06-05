package dev.temez.springlify.commander.annotation.filter;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;


@Order(0)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePermission {

  @NotNull String value();
}
