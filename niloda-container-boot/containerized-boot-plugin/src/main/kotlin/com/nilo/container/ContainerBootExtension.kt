package com.nilo.container

open class ContainerBootExtension(
        var awsProjectId: String = "",
        var awsRegion: String = "",
        var awsRepositoryName: String = "",
        var awsDigestFilename: String = "awsDigest.txt"
) {
    companion object {
        const val name = "containerBootExtension"
    }

}