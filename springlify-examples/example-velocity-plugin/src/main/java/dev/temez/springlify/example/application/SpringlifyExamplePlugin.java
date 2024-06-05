package dev.temez.springlify.example.application;

import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.starter.annotation.SpringlifyApplication;
import dev.temez.springlify.starter.plugin.SpringlifyVelocityPlugin;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.nio.file.Path;

@Plugin(
    id = "example-plugin",
    name = "example-plugin",
    version = "0.1",
    authors = {"temez"}
)
@SpringlifyApplication(
    applicationClass = SpringlifyExampleApplication.class
)
public class SpringlifyExamplePlugin extends SpringlifyVelocityPlugin {

  @Inject
  protected SpringlifyExamplePlugin(
      @NotNull ProxyServer server,
      @DataDirectory @NotNull Path dataFolder
  ) {
    super(server, dataFolder);
  }
}
