package dev.temez.springlify.commander.command.sender;

import org.jetbrains.annotations.NotNull;

/**
 * A factory interface for constructing sender details.
 * <p>
 * Implementations of this interface are responsible for creating sender details objects
 * based on the provided {@link Sender}.
 * </p>
 * <p>
 * The {@code SenderDetails} type parameter specifies the type of sender details object
 * that will be constructed by implementations of this interface.
 * </p>
 *
 * @param <T> The type of sender details to be constructed.
 * @see Sender
 * @since 0.7.0.0-RC1
 */
public interface SenderDetailsFactory<T> {

  /**
   * Constructs sender details based on the provided command sender.
   *
   * @param commandSender The command sender for which sender details are to be constructed.
   * @return The constructed sender details object.
   * @throws NullPointerException if {@code commandSender} is {@code null}.
   */
  @NotNull T getDetails(@NotNull Sender<?> commandSender);
}
