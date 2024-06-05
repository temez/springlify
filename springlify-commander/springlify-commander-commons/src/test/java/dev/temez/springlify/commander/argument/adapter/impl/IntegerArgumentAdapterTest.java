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
class IntegerArgumentAdapterTest {

  @NotNull
  Sender<?> sender = mock(Sender.class);

  @NotNull
  ArgumentAdapter<Integer> adapter = new IntegerArgumentAdapter();

  @Test
  void givenIntegerStringInput_whenParse_thenReturnIntegerNumber() {
    int number = adapter.parse(sender, "2");

    assertThat(number).isEqualTo(2);
  }

  @Test
  void givenInvalidInput_whenParse_thenThrowException() {
    assertThrows(ArgumentException.class, () -> adapter.parse(sender, "sew"));
  }

}