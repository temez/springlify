package dev.temez.springlify.commander.annotation.context;

import java.lang.annotation.*;

/**
 * An annotation used to mark commander components as external.
 * <p>
 * This annotation can be applied to filters, parameter adapters, and parameter completers to indicate that they are
 * external to the current module or system.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface External {
}
