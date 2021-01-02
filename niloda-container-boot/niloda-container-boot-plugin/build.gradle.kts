plugins {
    `maven-publish`
    `java-gradle-plugin`
    `kotlin-dsl`
    kotlin("jvm")
    id("com.nilo.NiloDependencies")
    id("org.jetbrains.kotlin.plugin.spring")
}


repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

gradlePlugin {
    plugins {
        create("com.nilo.ContainerBoot") {
            id = "com.nilo.ContainerBoot"
            version = "0.0.1"
            implementationClass = "com.nilo.container.ContainerBootPlugin"
        }
    }
}
