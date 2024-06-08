package dev.temez.springlify.commander.annotation.context;

import java.lang.annotation.*;

/**
 * An annotation used to mark commander components as global.
 * <p>
 * This annotation can be applied to simple command filters  to indicate that they are
 * global components, meaning they are available and applicable across all commands and contexts.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Global {
}
