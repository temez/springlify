package dev.temez.springlify.jpa.configuration.platform;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.buktify.configurate.annotation.Configuration;
import org.buktify.configurate.annotation.Variable;

/**
 * Configuration class for database settings.
 *
 * <p>This class is annotated with {@code @Configuration} to indicate that it holds configuration
 * properties. The configuration is loaded from a file named "database.yml"
 * located at "%plugin_root%/%filename%".
 *
 * <p>The class includes various properties for configuring the database, such as driver,
 * URL, username, password, additional Hibernate properties,
 * and whether Hikari connection pooling is enabled.
 *
 * <p>Default values are provided for some properties, and these can be overridden as needed.
 */
@Configuration(
    fileName = "database.yml",
    filePath = "%plugin_root%/%filename%"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@SuppressWarnings("FieldMayBeFinal")
public class DatabaseSettings {

  /**
   * The JDBC driver class for the database.
   */
  @Variable("hibernate.driver")
  String driver = "com.mysql.cj.jdbc.Driver";

  /**
   * The URL of the database.
   */
  @Variable("hibernate.url")
  String url = "jdbc:mysql://localhost:3306/test";

  /**
   * The username for connecting to the database.
   */
  @Variable("hibernate.username")
  String username = "test";

  /**
   * The password for connecting to the database.
   */
  @Variable("hibernate.password")
  String password = "uQwkDYAkHNXb";

  /**
   * Additional Hibernate properties for configuration.
   */
  @Variable("hibernate.additional-properties")
  List<String> additionalProperties = List.of(
      "hibernate.hbm2ddl.auto:update",
      "hibernate.show_sql:true"
  );

  /**
   * Indicates whether Hikari connection pooling is enabled.
   */
  @Variable("hikari.enabled")
  boolean hikariEnabled = true;
}
