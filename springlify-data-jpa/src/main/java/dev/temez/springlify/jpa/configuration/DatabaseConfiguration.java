package dev.temez.springlify.jpa.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import dev.temez.springlify.jpa.configuration.platform.DatabaseSettings;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.buktify.configurate.SimpleConfigurationService;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * Configuration class for setting up database-related beans using Spring and Hibernate.
 *
 * <p>This class provides Spring beans for configuring database settings, Hibernate properties,
 * data source, entity manager factory, transaction manager, and entity manager.
 */
@Configuration
public class DatabaseConfiguration {

  /**
   * Creates a bean for {@code DatabaseSettings}.
   *
   * @param velocityPlugin The Springlify plugin instance to retrieve the data folder.
   * @return An instance of {@code DatabaseSettings} configured using the provided plugin.
   */
  @Bean
  @SuppressWarnings("all")
  public DatabaseSettings databaseSettings(@NotNull SpringlifyPlugin velocityPlugin) {
    return new SimpleConfigurationService(velocityPlugin.getDataFolder())
        .registerConfigurations(DatabaseSettings.class)
        .apply()
        .getConfigurationPool()
        .getConfiguration(DatabaseSettings.class);
  }

  /**
   * Creates a bean for Hibernate properties using information from {@code DatabaseSettings}.
   *
   * @param databaseSettings The database settings used to populate Hibernate properties.
   * @return A {@code HashMap} containing Hibernate properties.
   */
  @Bean
  public HashMap<Object, Object> hibernateProperties(@NotNull DatabaseSettings databaseSettings) {
    return new HashMap<>() {
      {
        put("hibernate.connection.driver_class", databaseSettings.getDriver());
        put("hibernate.connection.url", databaseSettings.getUrl());
        put("hibernate.connection.username", databaseSettings.getUsername());
        put("hibernate.connection.password", databaseSettings.getPassword());

        for (String option : databaseSettings.getAdditionalProperties()) {
          String[] optionsEntry = option.split(":");
          put(optionsEntry[0], optionsEntry[1]);
        }
      }
    };
  }

  /**
   * Creates a bean for the data source based on the provided {@code DatabaseSettings}.
   *
   * @param databaseSettings The database settings used to configure the data source.
   * @return A data source bean, either {@code HikariDataSource} or {@code SimpleDriverDataSource}.
   */
  @Bean
  public DataSource dataSource(@NotNull DatabaseSettings databaseSettings) {
    if (databaseSettings.isHikariEnabled()) {
      HikariConfig hikariConfig = new HikariConfig();
      hikariConfig.setUsername(databaseSettings.getUsername());
      hikariConfig.setJdbcUrl(databaseSettings.getUrl());
      hikariConfig.setDriverClassName(databaseSettings.getDriver());
      hikariConfig.setPassword(databaseSettings.getPassword());
      return new HikariDataSource(hikariConfig);
    }
    return new SimpleDriverDataSource();
  }

  /**
   * Creates a bean for the {@code EntityManagerFactory} using Hibernate.
   *
   * @param hibernateProperties The Hibernate properties used to configure the entity manager
   *                            factory.
   * @return An {@code EntityManagerFactory} bean.
   */
  @Bean
  public EntityManagerFactory entityManagerFactory(
      @NotNull Map<Object, Object> hibernateProperties) {
    return new HibernatePersistenceProvider().createEntityManagerFactory("Dummy",
        hibernateProperties);
  }

  /**
   * Creates a bean for the {@code JpaTransactionManager} using the provided
   * {@code EntityManagerFactory}.
   *
   * @param entityManagerFactory The entity manager factory used to create the transaction manager.
   * @return A {@code JpaTransactionManager} bean.
   */
  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  /**
   * Creates a bean for the {@code EntityManager} using the provided {@code EntityManagerFactory}.
   *
   * @param entityManagerFactory The entity manager factory used to create the entity manager.
   * @return An {@code EntityManager} bean.
   */
  @Bean
  public EntityManager entityManager(@NotNull EntityManagerFactory entityManagerFactory) {
    return entityManagerFactory.createEntityManager();
  }
}
