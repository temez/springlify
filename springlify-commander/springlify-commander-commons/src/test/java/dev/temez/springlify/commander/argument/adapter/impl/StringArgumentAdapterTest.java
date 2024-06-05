package dev.temez.springlify.commander.argument.adapter.impl;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class StringArgumentAdapterTest {

  @NotNull
  Sender<?> sender = mock(Sender.class);

  @NotNull
  ArgumentAdapter<String> adapter = new StringArgumentAdapter();

  @Test
  void givenValidString_whenParse_thenReturnString() {
    String string = adapter.parse(sender, "test");

    assertThat(string).isEqualTo("test");
  }

}