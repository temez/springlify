package dev.temez.springlify.commander.commons.annotation;

import dev.temez.springlify.commander.commons.sender.SenderDetailsFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnableDetailsSupport {

  @NotNull Class<? extends SenderDetailsFactory<?>> value();
}

