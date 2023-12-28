package dev.temez.springlify.test.application;

import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.starter.commons.annotation.EnableSpringlifyStarter;
import dev.temez.springlify.velocity.plugin.SpringlifyVelocityPlugin;
import java.nio.file.Path;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code SpringlifyTestPlugin} class extends {@code SpringlifyBukkitPlugin} and serves
 * as the main plugin class for the Springlify integration in a Bukkit-based environment.
 *
 * <p>This plugin class specifies the main application class using the
 * {@code @EnableSpringlifyStarter} method. The associated Spring Boot application
 * is {@code SpringlifyTestApplication}.</p>
 *
 * @since 0.5.9.4dev
 */

@Plugin(
    id = "test-plugin",
    name = "test-plugin",
    version = "0.1",
    authors = {"temez"}
)
@EnableSpringlifyStarter(
    applicationClass = SpringlifyTestApplication.class
)
public class SpringlifyTestPlugin extends SpringlifyVelocityPlugin {

  /**
   * Constructs a new instance of {@code SpringlifyVelocityPlugin}.
   *
   * @param server     The Velocity proxy server.
   * @param dataFolder The data folder for the plugin.
   */
  @Inject
  public SpringlifyTestPlugin(
      @NotNull ProxyServer server,
      @NotNull @DataDirectory Path dataFolder) {
    super(server, dataFolder);
  }
}
