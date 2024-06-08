package dev.temez.springlify.commander.annotation;

import java.lang.annotation.*;


/**
 * Marks a method within a command handler class as the root command handler.
 * <p>
 * The method annotated with {@code @CommandRoot} serves as the entry point for a command hierarchy.
 * When a command is executed, the method annotated with {@code @CommandRoot} is invoked first to handle the command.
 * </p>
 *
 * @see CommanderCommand
 * @since 0.7.0.0-RC1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandRoot {

}


