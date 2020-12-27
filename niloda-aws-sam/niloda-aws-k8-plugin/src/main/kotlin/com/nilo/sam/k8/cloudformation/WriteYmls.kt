package com.nilo.sam.k8.cloudformation

import com.nilo.sam.k8.writeContents


object WriteYmls {
    operator fun invoke(generatedDir: String, files: List<FileToContents>) {
        files
                .map{ "$generatedDir/${it.name}.yml" to it.contents }
                .forEach { (file, contents) -> file.writeContents(contents) }

    }
}