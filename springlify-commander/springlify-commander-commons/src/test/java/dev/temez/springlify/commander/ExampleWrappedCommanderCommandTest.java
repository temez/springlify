package dev.temez.springlify.commander;

import dev.temez.springlify.commander.annotation.CommandRoot;
import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.argument.adapter.impl.IntegerArgumentAdapter;
import dev.temez.springlify.commander.argument.adapter.impl.StringArgumentAdapter;
import dev.temez.springlify.commander.argument.adapter.resolver.ContextArgumentAdapterResolver;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.CommandType;
import dev.temez.springlify.commander.command.completer.ProviderCommandCompleter;
import dev.temez.springlify.commander.command.completer.provider.impl.GenericCompletionProvider;
import dev.temez.springlify.commander.command.completer.provider.impl.SubCommandCompletionProvider;
import dev.temez.springlify.commander.command.discoverer.ClassBasedCommandDiscoverer;
import dev.temez.springlify.commander.command.discoverer.CommandDiscoverer;
import dev.temez.springlify.commander.command.discoverer.MethodBasedCommandDiscoverer;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.invocation.CommandInvocationImpl;
import dev.temez.springlify.commander.command.preprocessor.ExecutionPreprocessorServiceImpl;
import dev.temez.springlify.commander.command.preprocessor.impl.SubcommandPreprocessor;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.service.CommandService;
import dev.temez.springlify.commander.service.CommandServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {
    StringArgumentAdapter.class,
    IntegerArgumentAdapter.class,
    ContextArgumentAdapterResolver.class,
    ProviderCommandCompleter.class,
    ClassBasedCommandDiscoverer.class,
    MethodBasedCommandDiscoverer.class,
    ExampleWrappedCommanderCommandTest.TestCommand.class,
    SubCommandCompletionProvider.class,
    GenericCompletionProvider.class,
    SubcommandPreprocessor.class,
    ExecutionPreprocessorServiceImpl.class,
    CommandServiceImpl.class
}
)
@TestPropertySource(
    properties = "logging.level.dev.temez.springlify=debug"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExampleWrappedCommanderCommandTest {

  @Autowired
  CommandDiscoverer<Class<?>> commandDiscoverer;

  @Autowired
  CommandService commandService;

  @Test
  void tst() {
    Command command = commandDiscoverer.discover(TestCommand.class);
    CommandInvocation execution = new CommandInvocationImpl(
        mock(Sender.class),
        command,
        List.of("othersub")
    );

    System.out.println(commandService.complete(execution));
  }

  @CommanderCommand(
      name = "test",
      description = "commands.test.description",
      type = CommandType.SHARED
  )
  public static class TestCommand {

    @CommandRoot
    public void execute(@NotNull Sender<?> sender, @NotNull String arg) {

    }

    @CommanderCommand(
        name = "sub",
        description = "commands.test.sub.description"
    )
    public void executeSub(@NotNull Sender<?> sender, @NotNull Integer sosiPidor, @NotNull String AUEEE) {

    }

    @CommanderCommand(
        name = "othersub",
        description = "commands.test.othersub.description"
    )
    public static class OtherSubCommand {

      @CommandRoot
      public void execute(@NotNull Sender<?> sender) {

      }

      @CommanderCommand(
          name = "sub",
          description = "commands.test.sub.description"
      )
      public void executeSub(@NotNull Sender<?> sender, @NotNull Integer sosiPidor, @NotNull String AUEEE) {

      }
    }
  }
}
