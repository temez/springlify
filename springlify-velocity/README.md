# springlify-velocity

Here is the documentation for developing for the Velocity platform.

First, you need to create a Spring application class. Notice, spring application class and plugin main are not the same things.
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

Then, the main class of your plugin.

```java
@Plugin(
    id = "springlify-demo",
    name = "springlify-demo",
    version = "0.0.1",
    authors = {"temez"}
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class UniverseWrapperPlugin extends SpringlifyVelocityPlugin {


  @Inject
  public UniverseWrapperPlugin(@NotNull ProxyServer server,
                               @NotNull @DataDirectory Path dataFolder
  ) {
    super(server, dataFolder);
  }

  @Override
  public @NotNull Class<?> getApplicationClass() {
    return UniverseWrapperApplication.class;
  }
}

```

To build your project, use `./gradlew shadowJar` task.

Great, you are ready to implement the main functionality of your plugin.
The library includes some small additions to Spring to facilitate working with the Velocity API.

### Event Listeners

You don't need to register event listeners in the `EventManager`. Simply mark your class as `@VelocityEventHandler`.
During the initialization of Spring beans, they will be automatically registered.

```java
@VelocityEventHandler
public class SomeService {
  // Implementation
}
```

### Commands

During the initialization of Spring beans, command handlers will be registered automatically.
Just mark your command executor `@VelocityCommand`.

```java
@VelocityCommand(command = "status")
public class TestCommand implements SimpleCommand {
  // Implementation
}
```