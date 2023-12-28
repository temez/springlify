package dev.temez.springlify.platform.commons.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "springlify.platform")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpringlifyPlatformConfiguration {

}
