package dev.temez.springlify.commander.commons.help;

import dev.temez.springlify.commander.commons.chat.CommanderChatService;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.platform.commons.text.converter.TextConverter;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link CommandHelpService} that sends help messages to the command sender.
 *
 * @since 0.5.8.9dev
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandHelpServiceImpl implements CommandHelpService {

  @NotNull TextConverter messageConverter;

  @NotNull CommandHelpMessageFactory messageFactory;

  @NotNull CommanderChatService chatService;

  /**
   * {@inheritDoc}
   */
  @Override
  public void sendHelpMessage(@NotNull Sender<?> sender, @NotNull RegisteredCommand command) {
    command = command.getRootCommand();
    List<String> helpMessages = messageFactory.getHelpMessage(sender, command);
    Component component = Component.empty();
    for (int i = 0; i < helpMessages.size(); i++) {
      component = component.append(messageConverter.parse(helpMessages.get(i)));
      if (i != helpMessages.size() - 1) {
        component = component.append(Component.newline());
      }
    }
    chatService.sendMessage(sender, component);
  }
}
