package dev.temez.springlify.test.application;

import dev.temez.springlify.starter.bukkit.plugin.SpringlifyBukkitPlugin;
import dev.temez.springlify.starter.commons.annotation.EnableSpringlifyStarter;

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
@EnableSpringlifyStarter(
    applicationClass = SpringlifyTestApplication.class
)
public class SpringlifyTestPlugin extends SpringlifyBukkitPlugin {
}
