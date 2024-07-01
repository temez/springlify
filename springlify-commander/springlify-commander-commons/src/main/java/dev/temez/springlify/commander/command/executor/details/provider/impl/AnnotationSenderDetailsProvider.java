package dev.temez.springlify.commander.command.executor.details.provider.impl;

import dev.temez.springlify.commander.annotation.SenderDetailsSource;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.executor.details.provider.SenderDetailsProvider;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.command.sender.SenderDetailsFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * A {@link SenderDetailsProvider} implementation that resolves sender details based on method-level annotations.
 * <p>
 * This provider checks if the command method is annotated with {@link SenderDetailsSource}.
 * If so, it resolves sender details using the specified {@link SenderDetailsFactory}.
 * </p>
 *
 * @see SenderDetailsProvider
 * @see SenderDetailsSource
 * @see SenderDetailsFactory
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AnnotationSenderDetailsProvider implements SenderDetailsProvider {

  /**
   * The Spring application context for retrieving sender details factories.
   */
  @NotNull
  ApplicationContext applicationContext;

  /**
   * Determines whether this provider supports the given command.
   * <p>
   * This provider supports a command if its method is annotated with {@link SenderDetailsSource}.
   * </p>
   *
   * @param command The command to check support for.
   * @return {@code true} if the command's method is annotated with {@link SenderDetailsSource}, {@code false} otherwise.
   */
  @Override
  public boolean supports(@NotNull Command command) {
    CommandInvocationMetadata commandInvocationMetadata = command.getCommandInvocationMetadata();
    Method method = commandInvocationMetadata.getCommandMethod();
    return method.isAnnotationPresent(SenderDetailsSource.class);
  }

  /**
   * Resolves details about the sender of the given command.
   * <p>
   * It retrieves the {@link SenderDetailsFactory} specified in the {@link SenderDetailsSource} annotation
   * and uses it to obtain sender details.
   * </p>
   *
   * @param command The command whose sender details are to be resolved.
   * @param sender  The sender of the command.
   * @return The resolved sender details.
   */
  @Override
  @NotNull
  public Object getSenderDetails(@NotNull Command command, @NotNull Sender<?> sender) {
    CommandInvocationMetadata commandInvocationMetadata = command.getCommandInvocationMetadata();
    Method method = commandInvocationMetadata.getCommandMethod();
    SenderDetailsSource senderDetailsSource = method.getAnnotation(SenderDetailsSource.class);
    return applicationContext.getBean(senderDetailsSource.value()).getDetails(sender);
  }
}

