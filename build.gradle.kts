plugins {
    kotlin("jvm") version "1.3.72"
    id("com.nilo.NiloDependencies")
    id("com.nilo.AwsSam")
    id("com.nilo.AwsCloudStack")
    id("com.nilo.ContainerBoot") 
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

