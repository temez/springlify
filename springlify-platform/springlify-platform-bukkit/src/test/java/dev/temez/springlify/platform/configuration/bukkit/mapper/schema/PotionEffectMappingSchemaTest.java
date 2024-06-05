package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.PotionEffectConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class PotionEffectMappingSchemaTest {

  MappingSchema<PotionEffectConfiguration, PotionEffect> potionEffectMappingSchema = new PotionEffectMappingSchema();

  @Test
  void givenPotionEffectConfiguration_whenMap_thenReturnBukkitPotionEffect() {
    PotionEffectConfiguration potionEffectConfiguration = new PotionEffectConfiguration(
        PotionEffectType.ABSORPTION,
        Duration.ofSeconds(30),
        1,
        false,
        true,
        true
    );

    PotionEffect effect = potionEffectMappingSchema.map(potionEffectConfiguration);

    assertThat(effect).isNotNull();
    assertThat(effect.getType()).isEqualTo(PotionEffectType.ABSORPTION);
    assertThat(effect.getDuration()).isEqualTo(Duration.ofSeconds(30).toSeconds() * 20);
    assertThat(effect.getAmplifier()).isEqualTo(1);
    assertThat(effect.isAmbient()).isFalse();
    assertThat(effect.hasParticles()).isTrue();
    assertThat(effect.hasIcon()).isTrue();
  }
}