package com.nilo.sam.k8

import java.io.File

fun String.writeContents(contents: String) = File(this)
        .apply { parentFile.mkdirs() }
        .printWriter()
        .use { it.println(contents) }

fun String.upperFirstLetter() = replace("^.".toRegex()) { it.value.toUpperCase() }

fun String.dashToCamelCase() = replace("-([A-Za-z])".toRegex()) { it.value.toUpperCase() }

fun String.stripNonAlpha() = replace("[^A-Za-z]*".toRegex(), "")

fun String.stripNonAlphaLower() = stripNonAlpha().toLowerCase()

fun String.stripFromEnd(postfix: String) = replace("$postfix$".toRegex(), "")

fun String.toUpperCamelCase() =
        upperFirstLetter()
                .dashToCamelCase()
                .stripNonAlpha()