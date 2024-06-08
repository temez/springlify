# springlify-commander

Module for fast annotation-driven command implementation.
Let's look through example from `Bukkit` platform.

```java

@Slf4j
@CommanderCommand(
    name = "example",
    type = CommandType.SHARED
)
public class ExampleCommand {

  @CommandRoot
  public void execute(@NotNull CommandSender commandSender, @NotNull World world) {
    log.info("This is example command!");
  }

  @CommanderCommand(
      name = "sub",
      type = CommandType.INGAME
  )
  @RequirePermisison("permission.admin")
  public void executeSub(@NotNull Player player, @NotNull Integer someInteger) {
    log.info("This is simple admin subcommand!");
  }

  @CommanderCommand(
      name = "other",
      type = CommandType.SHARED
  )
  public static class SubExampleCommand {

    @CommandRoot
    public void execute(@NotNull CommandSender commandSender, @Completer(ExternalWorldCompleter.class) @NotNull World world) {
      log.info("This is other subcommand!");
    }

    @CommanderCommand(
        name = "sub",
        type = CommandType.INGAME
    )
    @SenderDetailsSource(ResidentDetailsFactory.class)
    public void executeSub(@NotNull Resident resident, @Adapter(ExternalIntegerAdapter.class) @NotNull Integer someInteger) {
      log.info("This is subcommand for sub subcommand for other!");
    }
  }
}
```

In this example:

- Commands are defined using the `@CommanderCommand` annotation, specifying the command name and type.
- The `@CommandRoot` annotation designates the method as the root command handler.
- Subcommands are defined within the same class or as nested classes, each annotated with @CommanderCommand.
- Method parameters are automatically injected based on the sender and provided arguments.
- Additional annotations like `@RequirePermission`, `@Completer`, `@SenderDetailsSource`, and `@Adapter` provide fine-grained
  control over command behavior and argument handling.

With `springlify-commander`, implementing complex command structures becomes more straightforward and maintainable,
allowing developers to focus on the functionality of their commands rather than boilerplate code.
