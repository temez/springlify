package dev.temez.springlify.example.application;

import dev.temez.springlify.starter.annotation.SpringlifyApplication;
import dev.temez.springlify.starter.plugin.SpringlifyBukkitPlugin;

@SpringlifyApplication(
    springApplicationClass = SpringlifyExampleApplication.class
)
public class SpringlifyExamplePlugin extends SpringlifyBukkitPlugin {

}
