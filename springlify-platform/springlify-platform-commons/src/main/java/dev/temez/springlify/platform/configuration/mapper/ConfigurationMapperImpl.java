package dev.temez.springlify.platform.configuration.mapper;

import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import dev.temez.springlify.platform.exception.MappingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConfigurationMapperImpl implements ConfigurationMapper {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public <S, R> @NotNull R map(@NotNull S source, @NotNull Class<R> resultClass) {
    Class<S> sourceClass = (Class<S>) source.getClass();
    MappingSchema<S, R> schema = getSchema(sourceClass, resultClass);
    return schema.map(source);
  }

  @Override
  public @NotNull <S, R> Collection<R> map(@NotNull Collection<S> source, @NotNull Class<R> resultClass) {
    throw new UnsupportedOperationException();
  }

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <S, R> MappingSchema<S, R> getSchema(
      @NotNull Class<S> sourceClass,
      @NotNull Class<R> resultClass
  ) throws MappingException {
    ResolvableType type = ResolvableType.forClassWithGenerics(MappingSchema.class, sourceClass, resultClass);
    String[] beanNamesForType = applicationContext.getBeanNamesForType(type);
    if (beanNamesForType.length == 0) {
      throw new MappingException("Schema not found for %s to %s".formatted(sourceClass, resultClass));
    }
    return (MappingSchema<S, R>) applicationContext.getBean(beanNamesForType[0]);
  }
}
