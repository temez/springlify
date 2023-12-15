# springlify-bukkit

Here is the documentation for developing for the Bukkit platform.

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

  @Bean
  SpringlifyDemoPlugin plugin() {
    return (SpringlifyDemoPlugin) Bukkit.getPluginManager().getPlugin("springlify-bukkit-demo");
  }
}
```

Then, the main class of your plugin.

```java
public final class SpringlifyDemoPlugin extends SpringlifyBukkitPlugin {

    @Override
    public @NotNull Class<?> getApplicationClass() {
        return SpringlifyDemoApplication.class;
    }
}
```

To build your project, use `./gradlew shadowJar` task.

Great, you are ready to implement the main functionality of your plugin.
The library includes some small additions to Spring to facilitate working with the Bukkit API.

### Event Listeners

You don't need to register event listeners in the `PluginManager`. Simply implement the `Listener` interface.
During the initialization of Spring beans, they will be automatically registered.

```java
@Service
public class SomeService implements Listener {
    // Implementation
}
```

### Commands

During the initialization of Spring beans, command handlers will be registered automatically.
Don't forget to add them to the `plugin.yml`. `TabCompleter` classes will be also registered.

```java
@BukkitCommand(command = "status")
public class TestCommand implements CommandExecutor {
    // Implementation
}
```