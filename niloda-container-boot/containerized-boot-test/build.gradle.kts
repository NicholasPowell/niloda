import com.nilo.dependencies.Kotlin
import com.nilo.dependencies.NiloDependency
import com.nilo.dependencies.NiloDependency.implementation
import com.nilo.dependencies.SpringBoot
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.nilo"
version = "0.0.1"

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("com.nilo.ContainerBoot") version "0.0.1"
    id("com.nilo.NiloDependencies")
    id("org.jetbrains.kotlin.plugin.spring")
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
tasks.withType<Test>().configureEach { useJUnitPlatform() }

dependencies {
    NiloDependency.implementation {
        from(Kotlin.WithVersion.sdk1_8)
        from(SpringBoot.WithVersion.actuator)
        from(SpringBoot.WithVersion.starterWebflux)
    }
}

