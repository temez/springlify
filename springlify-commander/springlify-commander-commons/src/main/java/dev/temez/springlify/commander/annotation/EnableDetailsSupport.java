package dev.temez.springlify.commander.annotation;

import dev.temez.springlify.commander.command.sender.SenderDetailsFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnableDetailsSupport {

  @NotNull Class<? extends SenderDetailsFactory<?>> value();
}
