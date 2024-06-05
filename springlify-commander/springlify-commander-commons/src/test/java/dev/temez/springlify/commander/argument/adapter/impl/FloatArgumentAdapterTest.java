package dev.temez.springlify.commander.argument.adapter.impl;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.ArgumentException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class FloatArgumentAdapterTest {

  @NotNull
  Sender<?> sender = mock(Sender.class);

  @NotNull
  ArgumentAdapter<Float> adapter = new FloatArgumentAdapter();

  @Test
  void givenFloatStringInput_whenParse_thenReturnFloatNumber() {
    float number = adapter.parse(sender, "1");

    assertThat(number).isEqualTo(1.0f);
  }

  @Test
  void givenInvalidInput_whenParse_thenThrowException() {
    assertThrows(ArgumentException.class, () -> adapter.parse(sender, "sew"));
  }
}