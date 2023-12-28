package dev.temez.springlify.commander.commons.validaiton.registry.global;

import dev.temez.springlify.commander.commons.exception.FormattedException;
import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GlobalFilterRegistryImpl implements GlobalFilterRegistry {

  @NotNull List<SimpleCommandFilter> globalCommandFilters = new ArrayList<>();

  @Override
  public @NotNull @Unmodifiable List<SimpleCommandFilter> getRegisteredFilters() {
    return List.copyOf(globalCommandFilters);
  }

  @Override
  public void register(@NotNull SimpleCommandFilter filter) throws FormattedException {
    if (globalCommandFilters.contains(filter)) {
      throw new FormattedException(
          "%s already registered in global filter registry!",
          filter.getClass().getSimpleName()
      );
    }
    globalCommandFilters.add(filter);
    log.info("Registered {} as global command filter", filter.getClass().getSimpleName());
  }
}
