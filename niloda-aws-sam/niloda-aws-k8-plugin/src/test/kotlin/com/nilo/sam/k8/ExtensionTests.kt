package com.nilo.sam.k8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

class ExtensionTests {

//    fun String.writeContents(contents: String) = File(this)
//            .apply { parentFile.mkdirs() }
//            .printWriter()
//            .use { it.println(contents) }
//


//
//
//    fun String.stripNonAlpha() = replace("[^A-Za-z]*".toRegex(), "")
//
//    fun String.stripFromEnd(postfix: String) = replace("$postfix$".toRegex(), "")
//
//    fun String.toUpperCamelCase() =
//            upperFirstLetter()
//                    .dashToCamelCase()
//                    .stripNonAlpha()
    @ParameterizedTest
    @CsvSource(value = [
        "simple,Simple",
        "AlreadyUpper,AlreadyUpper",
        "a,A",
        "with-several-dashes,With-several-dashes"
    ])
    fun testUpperFirstLetter(input: String, expected: String) {
        assertEquals(expected, input.upperFirstLetter())
    }

    @ParameterizedTest
    @CsvSource(value = [
        "simple,simple",
        "AlreadyUpper,AlreadyUpper",
        "a,a",
        "with-several-dashes,with-Several-Dashes",
        "alreadY-capS,alreadY-CapS",
        "alreadY-CapS,alreadY-CapS"
    ])
    fun testDashToCamelCase(input: String, expected: String) {
        assertEquals(expected, input.dashToCamelCase())
    }

    @ParameterizedTest
    @CsvSource(value = [
        "simple,Simple",
        "AlreadyUpper,AlreadyUpper",
        "a,A",
        "with-several-dashes,WithSeveralDashes",
        "alreadY-capS,AlreadYCapS",
        "alreadY-CapS,AlreadYCapS",
        "probablyOverkill-,ProbablyOverkill"
    ])
    fun testToUpperCamelCase(input: String, expected: String) {
        assertEquals(expected, input.toUpperCamelCase())
    }

    @ParameterizedTest
    @CsvSource(value = [
        "simple,simple",
        "AlreadyUpper,alreadyupper",
        "a,a",
        "with-several-dashes,withseveraldashes",
        "alreadY-capS,alreadycaps",
        "alreadY-CapS,alreadycaps",
        "probablyOverkill-,probablyoverkill"
    ])
    fun testStripNonAlphaLower(input: String, expected: String) {
        assertEquals(expected, input.stripNonAlphaLower())
    }


}