# springlify-starter

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

Then, the main class of your plugin. You should extend your class from `SpringlifyBukkitPlugin` or `SpringlifyVelocityPlugin`
depending on your platform.

```java
@SpringlifyApplication(
    springApplicationClass = SpringlifyExampleApplication.class
)
public final class SpringlifyDemoPlugin extends SpringlifyBukkitPlugin {


}
```

To build your project, use `./gradlew shadowJar` task.

You are ready to implement the main functionality of your plugin. Look through examples provided in `springlify-examples:example-<platform>-plugin`
and good luck. 