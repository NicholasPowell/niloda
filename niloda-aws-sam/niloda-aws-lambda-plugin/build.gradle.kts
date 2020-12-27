group = "com.nilo"

plugins {
    `java-gradle-plugin`
    kotlin("jvm")
    `kotlin-dsl`
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}
gradlePlugin {
    plugins {
        create("com.nilo.AwsLambda") {
            id = "com.nilo.AwsLambda"
            version = "0.0.1"
            implementationClass = "com.nilo.sam.lambda.AwsLambdaPlugin"
        }
    }
}
