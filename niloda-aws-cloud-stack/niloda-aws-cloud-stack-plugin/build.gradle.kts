import com.nilo.dependencies.Aws
import com.nilo.dependencies.Jackson
import com.nilo.dependencies.Kotlin
import com.nilo.dependencies.NiloDependency
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.nilo"
version = "0.0.1"

plugins {
    `java-gradle-plugin`
    kotlin("jvm")
    id("com.nilo.NiloDependencies")
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}


tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
tasks.withType<Test>().configureEach { useJUnitPlatform() }

dependencies {
    NiloDependency.compileOnly {
        from(Aws.dynamoClient,Kotlin.sdk1_8,Aws.WithVersion.serverlessJavaContainerSpringBoot)
        fromStarter(com.nilo.dependencies.NiloDependencyLayer.all)
        fromPlatform(com.nilo.dependencies.SpringBoot.platform, com.nilo.dependencies.SpringCloud.platform)
    }
}

gradlePlugin {
    plugins {
        create("com.nilo.AwsCloudStack") {
            id = "com.nilo.AwsCloudStack"
            version = "0.0.1"
            implementationClass = "com.nilo.cloudfxn.AwsCloudStackPlugin"
        }
    }
}