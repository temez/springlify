package dev.temez.springlify.starter.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface VelocityEventHandler {

  @AliasFor(annotation = Component.class)
  String value() default "";
}
