package dev.temez.springlify.commander.argument.adapter.resolver;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.exception.argument.ArgumentAdapterException;
import org.jetbrains.annotations.NotNull;


public interface ArgumentAdapterResolver {

  @NotNull <T> ArgumentAdapter<T> getAdapter(@NotNull Class<T> argumentType) throws ArgumentAdapterException;

}
