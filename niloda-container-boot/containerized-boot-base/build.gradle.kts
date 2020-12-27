import com.nilo.dependencies.Kotlin
import com.nilo.dependencies.NiloDependency
import com.nilo.dependencies.SpringBoot
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.nilo"
version = "0.0.1"

plugins {
    kotlin("jvm")
    id("com.nilo.NiloDependencies")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.spring")
    `maven-publish`
    id("com.palantir.docker")
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
tasks.withType<Test>().configureEach { useJUnitPlatform() }

dependencies {
    NiloDependency.api {
        from(Kotlin.WithVersion.sdk1_8)
        from(SpringBoot.WithVersion.actuator)
        from(SpringBoot.WithVersion.starterWebflux)
    }
}

tasks.named("docker") { dependsOn("bootJar")}

docker {
    name = "docker:name"
    tag("hmm", "stuff")
    files("build/libs/containerized-boot-base-0.0.1.jar")
    buildArgs(
            mapOf(
                    "JAR_FILE" to "containerized-boot-base-0.0.1.jar"
            )
    )
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.nilo"
            artifactId = "containerized-boot-base"
            version = "0.0.1"
            from(components["java"])
        }
    }
}