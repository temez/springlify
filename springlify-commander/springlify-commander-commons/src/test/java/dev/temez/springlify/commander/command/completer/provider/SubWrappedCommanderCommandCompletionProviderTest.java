package dev.temez.springlify.commander.command.completer.provider;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.completer.provider.impl.SubCommandCompletionProvider;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubWrappedCommanderCommandCompletionProviderTest {

  @NotNull
  CompletionProvider completionProvider = new SubCommandCompletionProvider();

  @Test
  void givenCommandWithoutSubcommands_whenComplete_thenReturnEmptyList() {
    Command command = mock(Command.class);
    when(command.getSubcommands()).thenReturn(Collections.emptyList());

    CommandInvocation commandInvocation = mock(CommandInvocation.class);
    when(commandInvocation.getCommand()).thenReturn(command);

    List<String> completion = completionProvider.complete(commandInvocation);

    assertThat(completion).isNotNull();
    assertThat(completion).isEmpty();
    assertThat(completion).isUnmodifiable();
  }

  @Test
  void givenCommandWithSubcommands_whenComplete_thenReturnSubcommandsList() {
    Command command = mock(Command.class);

    Command subCommand = mock(Command.class);
    when(subCommand.getName()).thenReturn("sub");

    when(command.getSubcommands()).thenReturn(List.of(subCommand));

    CommandInvocation commandInvocation = mock(CommandInvocation.class);
    when(commandInvocation.getCommand()).thenReturn(command);

    List<String> completion = completionProvider.complete(commandInvocation);

    assertThat(completion).isNotNull();
    assertThat(completion.size()).isEqualTo(1);
    assertThat(completion.get(0)).isEqualTo("sub");
    assertThat(completion).isUnmodifiable();
  }
}