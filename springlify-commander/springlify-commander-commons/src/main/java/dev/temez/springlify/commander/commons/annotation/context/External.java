package dev.temez.springlify.commander.commons.annotation.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated type is external.
 * This annotation is intended to be used on classes, interfaces, enums, etc.
 *
 * @since 0.5.8.9dev
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface External {

}
