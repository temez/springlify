package dev.temez.springlify.starter.commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * A ClassLoader implementation that iterates over a collection of other ClassLoaders until it
 * finds everything it's looking for.
 */
@SuppressWarnings("all")
public final class CompoundClassLoader extends ClassLoader {

  /**
   * Загрузчики которые чекаем в первую очередь, так же отсюда можем тянуть ресурсы
   */
  private final Collection<ClassLoader> defaultLoaders;

  /**
   * Constructs a new CompoundClassLoader.
   *
   * @param loaders the loaders to iterate over
   */
  public CompoundClassLoader(ClassLoader... loaders) {
    defaultLoaders = Arrays.asList(loaders);
  }

  /**
   * Constructs a new CompoundClassLoader.
   *
   * @param loaders the loaders to iterate over
   */
  public CompoundClassLoader(Collection<ClassLoader> loaders) {
    defaultLoaders = loaders;
  }

  private Optional<ClassLoader> spigotWorkaround(String name) {
    return spigotWorkaroundClass(name).map(Class::getClassLoader);
  }

  private Optional<Class<?>> spigotWorkaroundClass(String name) {
    if (!name.endsWith(".class")) {
      return Optional.empty();
    }
    // Если это не класс, то это может быть какой-то ресурс чужого плагина, оно нам не надо

    name = name.replace("/", ".");
    name = name.substring(0, name.length() - ".class".length());

    Class<?> foundClass = null;
    for (ClassLoader loader : defaultLoaders) {
      try {
        foundClass = Class.forName(name, false, loader);
        break;
      } catch (ClassNotFoundException ignored) {

      }
    }

    return Optional.ofNullable(foundClass);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public URL getResource(String name) {
    for (ClassLoader loader : defaultLoaders) {
      if (loader != null) {
        URL resource = loader.getResource(name);
        if (resource != null) {
          return resource;
        }
      }
    }

    return spigotWorkaround(name).map(loader -> loader.getResource(name)).orElse(null);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public InputStream getResourceAsStream(String name) {
    for (ClassLoader loader : defaultLoaders) {
      if (loader != null) {
        InputStream is = loader.getResourceAsStream(name);
        if (is != null) {
          return is;
        }
      }
    }

    return spigotWorkaround(name).map(loader -> loader.getResourceAsStream(name)).orElse(null);
  }

  // В этом методе юзаются только ресурсы самого плагина, не буду сюда все плаги пихать, а то каша будет
  @Override
  public Enumeration<URL> getResources(String name) throws IOException {
    List<URL> urls = new ArrayList<>();
    for (ClassLoader loader : defaultLoaders) {
      if (loader != null) {
        try {
          Enumeration<URL> resources = loader.getResources(name);
          while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource != null && !urls.contains(resource)) {
              urls.add(resource);
            }
          }
        } catch (IOException ignored) {
        }
      }
    }

    return Collections.enumeration(urls);
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    for (ClassLoader loader : defaultLoaders) {
      if (loader != null) {
        try {
          return loader.loadClass(name);
        } catch (ClassNotFoundException ignored) {
        }
      }
    }
    throw new ClassNotFoundException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    return loadClass(name);
  }

  @Override
  public String toString() {
    return String.format("CompoundClassloader %s", defaultLoaders);
  }
}