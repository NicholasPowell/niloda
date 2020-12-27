pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
        jcenter()
    }
}

include(":dependency-management")

rootProject.name = "niloda-dependency-management"