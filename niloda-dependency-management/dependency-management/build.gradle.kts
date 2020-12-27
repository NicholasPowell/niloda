plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

group = "com.nilo"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

tasks.withType<Test>().configureEach { useJUnitPlatform() }

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
    implementation(gradleApi())
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
}

gradlePlugin {
    plugins {
        create("com.nilo.NiloDependencies") {
            id = "com.nilo.NiloDependencies"
            implementationClass = "com.nilo.dependencies.NiloDependencyPlugin"
            version = "0.0.1"
        }
    }
}