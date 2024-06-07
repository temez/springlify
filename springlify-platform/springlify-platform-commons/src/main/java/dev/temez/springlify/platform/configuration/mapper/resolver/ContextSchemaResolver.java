package dev.temez.springlify.platform.configuration.mapper.resolver;

import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import dev.temez.springlify.platform.exception.MappingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

/**
 * Component for resolving mapping schemas from the application context.
 *
 * <p>This component implements the {@link SchemaResolver} interface and retrieves mapping schemas
 * from the application context based on the specified source and result classes.</p>
 *
 * @see SchemaResolver
 * @see MappingSchema
 * @see MappingException
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContextSchemaResolver implements SchemaResolver {

  /**
   * The application context used for retrieving mapping schemas.
   */
  @NotNull
  ApplicationContext applicationContext;

  /**
   * Retrieves the mapping schema for mapping between the specified source and result classes.
   *
   * @param <S>         the type of the source objects
   * @param <R>         the type of the result objects
   * @param sourceClass the class of the source objects
   * @param resultClass the class of the result objects
   * @return the mapping schema for the specified source and result classes
   * @throws MappingException if there is an error retrieving the mapping schema
   */
  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <S, R> MappingSchema<S, R> getSchema(@NotNull Class<S> sourceClass, @NotNull Class<R> resultClass) throws MappingException {
    ResolvableType type = ResolvableType.forClassWithGenerics(MappingSchema.class, sourceClass, resultClass);
    String[] beanNamesForType = applicationContext.getBeanNamesForType(type);
    if (beanNamesForType.length == 0) {
      throw new MappingException("Schema not found for %s to %s".formatted(sourceClass, resultClass));
    }
    if (beanNamesForType.length > 1) {
      throw new MappingException("Multiple schemas found for %s to %s".formatted(sourceClass, resultClass));
    }
    return (MappingSchema<S, R>) applicationContext.getBean(beanNamesForType[0]);
  }
}

