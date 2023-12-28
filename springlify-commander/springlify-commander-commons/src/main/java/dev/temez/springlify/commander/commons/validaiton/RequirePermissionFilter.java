package dev.temez.springlify.commander.commons.validaiton;

import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.annotation.RequirePermission;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class RequirePermissionFilter implements CommandFilter<RequirePermission> {
  @Override
  public @NotNull Class<RequirePermission> getFilterAnnotationType() {
    return RequirePermission.class;
  }

  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull RequirePermission annotation)
      throws ValidationException {
    if (!sender.hasPermission(annotation.value())) {
      throw new ValidationException(
          "commander.filter.permission.no-permission"
      );
    }
  }
}
