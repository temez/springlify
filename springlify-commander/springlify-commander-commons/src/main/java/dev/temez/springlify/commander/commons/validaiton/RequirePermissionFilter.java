package dev.temez.springlify.commander.commons.validaiton;

import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.annotation.RequirePermission;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Command filter for checking the required permission specified by
 * the {@link RequirePermission} annotation.
 *
 * @since 0.5.8.9dev
 */
@Component
public final class RequirePermissionFilter implements CommandFilter<RequirePermission> {

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Class<RequirePermission> getFilterAnnotationType() {
    return RequirePermission.class;
  }

  /**
   * {@inheritDoc}
   *
   * @throws ValidationException if the sender does not have the required permission.
   */
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
