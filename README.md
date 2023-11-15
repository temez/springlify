# springlify

```groovy
maven {
    url = "https://gitlab.com/api/v4/groups/76713909/-/packages/maven"
    name = "GitLab"
    boolean isCiTask = System.getenv("CI_JOB_TOKEN") != null
    credentials(HttpHeaderCredentials) {
        name = isCiTask ? 'Job-Token' : 'Private-Token'
        value = isCiTask ? System.getenv("CI_JOB_TOKEN") : System.getenv("GITLAB_PRIVATE_TOKEN") //Ваш GitLab токен
    }
    authentication {
        header(HttpHeaderAuthentication)
    }
}
```
```groovy
    implementation 'dev.temez.springlify:springlify-bukkit:0.5.9.1dev'
```


This library enables the use of `Spring Framework` for developing on Bukkit and Velocity platforms.
If you need, you can implement specific platform solution for any server core.

To use this library, simply include it as an implementation dependency.
`spring-boot-starter` will be automatically added to your project.

Next, select a link to the documentation for your platform.
- [Bukkit documentation](/springlify-bukkit/README.md)
- [Velocity documentation](/springlify-velocity/README.md)

