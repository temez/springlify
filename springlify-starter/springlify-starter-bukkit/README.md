# springlify-starter

First, you need to create a Spring application class. Notice, spring application class and plugin main are not the same
things.
Specify base `scanBasePackages` for scanning through the library classes and our application ones.

```java
@SpringBootApplication(
    scanBasePackages = {
        "dev.temez.springlify",
        "dev.temez.demo"
    }
)
public class SpringlifyDemoApplication {

}
```

Then, the main class of your plugin. You should extend your class from `SpringlifyBukkitPlugin`.

```java
@SpringlifyApplication(
    springApplicationClass = SpringlifyExampleApplication.class
)
public final class SpringlifyDemoPlugin extends SpringlifyBukkitPlugin {


}
```

To build your project, use `./gradlew shadowJar` task.

You are now ready to implement the main functionality of your plugin.
Refer to the examples provided in `springlify-examples:example-bukkit-plugin` for guidance and good luck.

### Other starter features

#### 1. Event handler auto registration

Starter has a build in `EventHandlerInitializer`.
It automatically will register and unregister a classes, which implement `Listener` as Bukkit event listeners.

```java

@Component
public class PlayerJoinListener implements Listener {

  @EventHandler
  public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
    event.getPlayer().sendMessage("Welcome to the server!");
  }
}
```

#### 2. Command executor and completer auto registration

Starter has a build in `CommandHandlerInitializer`. It will register classes which implement
`CommandExecutor` and `TabCompleter` as Bukkit command executors and completers.

```java

@BukkitCommand(command = "test")
public class HelloCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
    sender.sendMessage("Hello world!");
    return true;
  }
}
```