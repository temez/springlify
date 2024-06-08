package dev.temez.springlify.commander.command.executor.details;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.executor.details.provider.SenderDetailsProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.details.SenderDetailsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of {@link SenderDetailsResolver} that delegates the resolution to a list of {@link SenderDetailsProvider}s.
 *
 * @see SenderDetailsProvider
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProviderSenderDetailsResolver implements SenderDetailsResolver {

  @NotNull
  List<SenderDetailsProvider> providers;

  /**
   * Resolves details about the sender of the given command by delegating to a list of {@link SenderDetailsProvider}s.
   *
   * @param command The command whose sender details are to be resolved.
   * @param sender  The sender of the command.
   * @return The resolved sender details.
   * @throws SenderDetailsException If an error occurs while resolving sender details.
   */
  @Override
  public @NotNull Object resolve(@NotNull Command command, @NotNull Sender<?> sender) throws SenderDetailsException {
    List<SenderDetailsProvider> supportedProviders = providers.stream()
        .filter(provider -> provider.supports(command))
        .toList();
    if (supportedProviders.isEmpty()) {
      throw new SenderDetailsException("No matching provider found for command method %s"
          .formatted(command.getCommandInvocationMetadata().getCommandMethod()));
    }
    if (supportedProviders.size() > 1) {
      throw new SenderDetailsException("No unique provider found for command method %s"
          .formatted(command.getCommandInvocationMetadata().getCommandMethod()));
    }
    return supportedProviders.get(0).getSenderDetails(command, sender);
  }
}
