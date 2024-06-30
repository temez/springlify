package dev.temez.springlify.platform.annotation;

import dev.temez.springlify.platform.condition.OnPluginEnabledCondition;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;


@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnPluginEnabledCondition.class)
public @interface ConditionalOnPluginEnabled {

    @NotNull String value();

}