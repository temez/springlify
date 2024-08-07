# springlify

# WARNING! Library is unstable and currently in development.

<a href="https://repo.star-mc.ru/#/public/dev/temez/springlify">
    <img src="https://repo.star-mc.ru/api/badge/latest/public/dev/temez/springlify/springlify-platform?color=40c14a&name=Lastest version&prefix=v"  alt=""/>
</a>

### dependencies

#### Gradle

````groovy
dependencies {
    implementation 'dev.temez.springlify:springlify-<module>-<platform>:0.7.2.1-SNAPSHOT'
}

repositories {
    mavenCentral()
    maven {
        name = "StarMC"
        url = "https://repo.star-mc.ru/public"
    }
}
````
#### Maven

```xml
<dependency>
  <groupId>dev.temez.springlify</groupId>
  <artifactId>springlify-<module>-<platform></artifactId>
  <version>0.7.2.0-SNAPSHOT</version>
</dependency>

<repository>
  <id>starmc-repo-public</id>
  <name>StarMC repositories</name>
  <url>https://repo.star-mc.ru/public</url>
</repository>
```

This library provides complex solutions for developing on Bukkit/Velocity platforms. It enables usage
of `Spring Framework` and contains some utilities to make it easier on Minecraft related platforms.

Library consists of `3 major modules`. Each module has common submodule, and platform-specific implementation.

### springlify-starter

A core module of library, which enables usage of `Spring Framework` and has minor platform-specific improvements.

### springlify-platform

An optional module, contains utility classes and abstraction level for localization and configuring your plugins.

### springlify-commander

Library for fast annotation-driven command implementation.

Next, select a link to the module documentation.

- [springlify-starter](/springlify-starter/README.md)
- [springlify-platform](/springlify-platform/README.md)
- [springlify-commander](/springlify-commander/README.md)

