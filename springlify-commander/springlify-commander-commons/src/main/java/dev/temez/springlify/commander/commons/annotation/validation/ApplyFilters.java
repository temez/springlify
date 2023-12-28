package dev.temez.springlify.commander.commons.annotation.validation;

import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface ApplyFilters {

  @NotNull Class<? extends SimpleCommandFilter>[] value();
}
