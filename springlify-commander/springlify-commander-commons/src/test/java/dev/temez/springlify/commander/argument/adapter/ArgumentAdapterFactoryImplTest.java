package dev.temez.springlify.commander.argument.adapter;

import dev.temez.springlify.commander.argument.adapter.impl.FloatArgumentAdapter;
import dev.temez.springlify.commander.exception.ArgumentAdapterException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class ArgumentAdapterFactoryImplTest {

  @NotNull
  ApplicationContext applicationContext = mock(ApplicationContext.class);

  @NotNull
  ArgumentAdapterFactory argumentAdapterFactory = new ArgumentAdapterFactoryImpl(applicationContext);

  @Test
  void givenFloatArgumentAdapterInContext_whenGetAdapter_thenReturnArgumentAdapter() {
    when(applicationContext.getBeanNamesForType(any(ResolvableType.class))).thenReturn(
        new String[]{"floatArgumentAdapter"}
    );
    when(applicationContext.getBean("floatArgumentAdapter")).thenReturn(new FloatArgumentAdapter());

    ArgumentAdapter<Float> adapter = argumentAdapterFactory.getAdapter(Float.class);

    assertThat(adapter).isNotNull();
    assertThat(adapter).isInstanceOf(FloatArgumentAdapter.class);
  }

  @Test
  void givenMultipleAdaptersInContext_whenGetAdapter_thenThrowException() {
    when(applicationContext.getBeanNamesForType(any(ResolvableType.class))).thenReturn(
        new String[]{"adapterA", "adapterB"}
    );

    assertThrows(ArgumentAdapterException.class, () -> argumentAdapterFactory.getAdapter(Float.class));
  }

  @Test
  void givenNoAdaptersInContext_whenGetAdapter_thenThrowException() {
    when(applicationContext.getBeanNamesForType(any(ResolvableType.class))).thenReturn(
        new String[]{}
    );

    assertThrows(ArgumentAdapterException.class, () -> argumentAdapterFactory.getAdapter(Float.class));
  }

}