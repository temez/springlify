package dev.temez.springlify.test.configuration;

import dev.temez.springlify.platform.bukkit.configuration.bukkit.ConfigurableLocation;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "configuration")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestConfiguration {

  @NotNull
  List<ConfigurableLocation> locations;

  @PostConstruct
  private void onInit() {
    System.out.println(this);
  }
}