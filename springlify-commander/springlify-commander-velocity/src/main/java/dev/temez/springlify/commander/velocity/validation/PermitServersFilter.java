package dev.temez.springlify.commander.velocity.validation;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.CommandFilter;
import dev.temez.springlify.commander.velocity.validation.annotation.PermitServers;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link CommandFilter} for the {@link PermitServers} annotation.
 *
 * @since 0.5.8.9dev
 */
@Component
public final class PermitServersFilter implements CommandFilter<PermitServers> {

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Class<PermitServers> getFilterAnnotationType() {
    return PermitServers.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void filter(
      @NotNull Sender<?> sender,
      @NotNull PermitServers annotation
  ) throws ValidationException {
    CommandSource commandSource = (CommandSource) sender.getPlatformSender();
    if (commandSource instanceof Player player) {
      List<String> restrictedServers = Arrays.stream(annotation.value()).toList();
      Optional<ServerConnection> connection = player.getCurrentServer();
      if (connection.isEmpty()) {
        return;
      }
      String currentServerId = connection.get().getServerInfo().getName();
      if (restrictedServers.stream().noneMatch(currentServerId::equals)) {
        throw new ValidationException(
            "commander.filter.permitted-servers.this-command-is-restricted-here"
        );
      }
    }
  }
}
