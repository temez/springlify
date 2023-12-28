package dev.temez.springlify.commander.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation indicating that the annotated method serves as a root command handler.
 * This annotation is intended to be used on methods.
 *
 * @since 0.5.8.9dev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RootCommand {
}
