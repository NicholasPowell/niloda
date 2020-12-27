package com.nilo.sam.lambda

import java.io.File
import java.util.concurrent.TimeUnit


fun String.runCommand(workingDir: File): Process? {
    return try {
        val parts = listOf("/bin/sh", "-c", this )
        val proc = ProcessBuilder(*parts.toTypedArray())
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()
        println("Running: $proc")
        proc.waitFor(60, TimeUnit.MINUTES)
        proc
    } catch(e: Exception) {
        e.printStackTrace()
        null
    }
}