package dev.temez.springlify.commander.commons.validaiton.annotation;

import dev.temez.springlify.commander.commons.annotation.validation.ValidateWith;
import dev.temez.springlify.commander.commons.validaiton.RequirePermissionFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;

@Order(0)
@ValidateWith(RequirePermissionFilter.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePermission {

  @NotNull String value();
}
