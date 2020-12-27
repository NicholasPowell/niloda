pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.nilo.NiloDependencies") {
                useModule("com.nilo:dependency-management:0.0.1")
            }
            if (requested.id.id == "com.nilo.AwsSam") {
                useModule("com.nilo:aws-sam-plugin:0.0.1")
            }
            if (requested.id.id == "com.nilo.AwsCloudStack") {
                useModule("com.nilo:aws-cloud-stack-plugin:0.0.1")
            }
            if (requested.id.id == "com.nilo.ContainerBoot") {
                useModule("com.nilo:containerized-boot-plugin:0.0.1")
            }
        }
    }
}

val includeLocalDependencies = true
if( includeLocalDependencies ) {
    includeBuild("niloda-aws-cloud-stack")
    includeBuild("niloda-aws-sam")
    includeBuild("niloda-container-boot")
    includeBuild("niloda-dependency-management")
}

rootProject.name="niloda"