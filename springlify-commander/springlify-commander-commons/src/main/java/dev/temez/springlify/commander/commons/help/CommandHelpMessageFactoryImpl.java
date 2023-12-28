package dev.temez.springlify.commander.commons.help;

import dev.temez.springlify.commander.commons.annotation.help.Hint;
import dev.temez.springlify.commander.commons.chat.CommanderLocalizationService;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.CommandFilterService;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandHelpMessageFactoryImpl implements CommandHelpMessageFactory {

  @NotNull CommanderLocalizationService localizationService;

  @NotNull CommandFilterService commandFilterService;

  @Override
  public @NotNull List<String> getHelpMessage(
      @NotNull Sender<?> sender,
      @NotNull RegisteredCommand command
  ) {
    List<String> result = new ArrayList<>();
    result.add(
        localizationService.localizeString(
            sender,
            "commander.help.header-template",
            command.getName()
        )
    );
    result.add(getCommandUsage(sender, command));
    List<RegisteredCommand> accessibleSubcommands = command
        .getSubcommands()
        .stream()
        .filter(sub -> commandFilterService.isAccessible(sender, sub))
        .toList();

    if (accessibleSubcommands.isEmpty()) {
      return result;
    }

    result.add(
        localizationService.localizeString(sender, "commander.help.subcommands-section")
    );
    accessibleSubcommands
        .forEach(subcommand -> result.add(getCommandUsage(sender, subcommand))
        );
    return result;
  }

  private @NotNull String getParameterDescription(
      @NotNull Sender<?> sender,
      @NotNull Parameter parameter
  ) {
    Hint hintAnnotation = parameter.getAnnotation(Hint.class);
    if (hintAnnotation == null) {
      return String.format("<%s>", parameter.getName());
    }
    return localizationService.localizeString(sender, hintAnnotation.value());
  }

  private @NotNull String getCommandUsage(
      @NotNull Sender<?> sender,
      @NotNull RegisteredCommand command
  ) {
    String argumentsHelpString = String.join(" ", command.getExecutionContext().getParameters()
        .stream()
        .map(p -> getParameterDescription(sender, p))
        .toList()
    );
    if (!argumentsHelpString.isEmpty()) {
      argumentsHelpString += " ";
    }
    return localizationService.localizeString(
        sender,
        "commander.help.usage-template",
        command.getFullName(),
        argumentsHelpString,
        localizationService.localizeString(sender, command.getDescription())
    );
  }
}
