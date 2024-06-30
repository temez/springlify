package dev.temez.springlify.platform.configuration;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KyoriAdventureConfiguration {

  @Bean
  @ConditionalOnExpression(
      "'${springlify.platform.text-converter-mode}'.equalsIgnoreCase('mini_message')"
  )
  @ConditionalOnMissingBean(MiniMessage.class)
  MiniMessage miniMessage() {
    return MiniMessage.builder().build();
  }

  @Bean
  @ConditionalOnExpression(
      "'${springlify.platform.text-converter-mode}'.equalsIgnoreCase('legacy')"
  )
  @ConditionalOnMissingBean(LegacyComponentSerializer.class)
  LegacyComponentSerializer legacyComponentSerializer() {
    return LegacyComponentSerializer.legacyAmpersand();
  }
}
