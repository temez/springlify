# springlify

This library enables the use of `Spring Framework` for developing on Bukkit and Velocity platforms.
If you need, you can implement specific platform solution for any server core.

To use this library, simply include it as an implementation dependency.
`spring-boot-starter` will be automatically added to your project.

```groovy
plugins {
    id 'java'
    id 'com.github.johnrengelman.shadow' version '8.1.0'
    id 'org.springframework.boot' version '3.0.4'
    id 'io.spring.dependency-management' version '1.1.0'
}

repositories {
    maven {
        name = 'StarMC'
        url = 'https://repo.star-mc.ru/public'
    }
}

dependencies {
    implementation 'dev.temez.springlify:springlify-bukkit:0.5.7dev'

    //rest of dependencies...
}
```

Next, select a link to the documentation for your platform.
- [Bukkit documentation](/springlify-bukkit/README.md)
- [Velocity documentation](/springlify-velocity/README.md)

