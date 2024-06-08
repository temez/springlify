package dev.temez.springlify.platform.configuration.mapper.resolver;

import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import dev.temez.springlify.platform.exception.MappingException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class ContextSchemaResolverTest {

  @NotNull
  ApplicationContext applicationContext = mock(ApplicationContext.class);

  @NotNull
  SchemaResolver schemaResolver = new ContextSchemaResolver(applicationContext);

  @Test
  void givenApplicationContextWithNoSchemas_whenGetSchema_thenThrowException() {
    when(applicationContext.getBeanNamesForType(any(ResolvableType.class))).thenReturn(new String[]{});
    assertThrows(MappingException.class, () -> schemaResolver.getSchema(Test.class, Test.class));
  }

  @Test
  void givenApplicationContextWithMultipleSchemas_whenGetSchema_thenThrowException() {
    when(applicationContext.getBeanNamesForType(any(ResolvableType.class))).thenReturn(new String[]{"schema1", "schema2"});
    assertThrows(MappingException.class, () -> schemaResolver.getSchema(Test.class, Test.class));
  }


  @Test
  void givenApplicationContextWithSchema_whenGetSchema_thenReturnSchema(@Mock MappingSchema<Test, Test> mappingSchema) {
    when(applicationContext.getBeanNamesForType(any(ResolvableType.class))).thenReturn(new String[]{"schema1"});
    when(applicationContext.getBean("schema1")).thenReturn(mappingSchema);

    MappingSchema<Test, Test> resolvedSchema = schemaResolver.getSchema(Test.class, Test.class);

    assertThat(resolvedSchema).isNotNull();
    assertThat(resolvedSchema).isEqualTo(mappingSchema);
  }

}