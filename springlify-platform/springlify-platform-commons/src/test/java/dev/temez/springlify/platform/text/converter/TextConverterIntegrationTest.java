package dev.temez.springlify.platform.text.converter;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
    LegacyTextConverter.class,
    MiniMessageTextConverter.class,
})
class TextConverterIntegrationTest {

  @Nested
  @TestPropertySource(properties = {
      "springlify.platform.text-converter-mode=legacy"
  })
  @FieldDefaults(level = AccessLevel.PRIVATE)
  class LegacyTextConverterTst {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void givenLegacyTextConverterEnabled_whenApplicationContextInitialized_thenTextConverterShouldBeLegacyTextConverter() {
      assertThat(applicationContext.getBean(TextConverter.class)).isInstanceOf(LegacyTextConverter.class);
    }
  }

  @Nested
  @TestPropertySource(properties = {
      "springlify.platform.text-converter-mode=mini_message"
  })
  @FieldDefaults(level = AccessLevel.PRIVATE)
  class MiniMessageTextConverterTst {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void givenLegacyTextConverterEnabled_whenApplicationContextInitialized_thenTextConverterShouldBeLegacyMiniMessageTextConverter() {
      assertThat(applicationContext.getBean(TextConverter.class)).isInstanceOf(MiniMessageTextConverter.class);
    }
  }
}