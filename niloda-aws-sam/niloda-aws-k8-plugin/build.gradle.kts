import com.nilo.dependencies.JUnit5
import com.nilo.dependencies.NiloDependency

group = "com.nilo"

plugins {
    `java-gradle-plugin`
    kotlin("jvm")
    `kotlin-dsl`
    `maven-publish`
    id("com.nilo.NiloDependencies")
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}
gradlePlugin {
    plugins {
        create("com.nilo.AwsK8") {
            id = "com.nilo.AwsK8"
            version = "0.0.1"
            implementationClass = "com.nilo.sam.k8.AwsK8Plugin"
        }
        create("com.nilo.AwsCluster") {
            id = "com.nilo.AwsCluster"
            version = "0.0.1"
            implementationClass = "com.nilo.sam.k8.AwsClusterPlugin"
        }
    }
}

tasks.withType<Test>().configureEach { useJUnitPlatform() }

dependencies {
    NiloDependency.testImplementation {
        from(JUnit5.WithVersion.api, JUnit5.WithVersion.engine, JUnit5.WithVersion.params)
    }
}
