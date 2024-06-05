package dev.temez.springlify.commander.argument.adapter;

import dev.temez.springlify.commander.exception.ArgumentAdapterException;
import org.jetbrains.annotations.NotNull;


public interface ArgumentAdapterFactory {

  @NotNull <T> ArgumentAdapter<T> getAdapter(@NotNull Class<T> argumentType) throws ArgumentAdapterException;

}
