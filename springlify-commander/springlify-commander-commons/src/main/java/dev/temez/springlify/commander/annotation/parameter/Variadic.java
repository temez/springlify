package dev.temez.springlify.commander.annotation.parameter;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Variadic {

}
