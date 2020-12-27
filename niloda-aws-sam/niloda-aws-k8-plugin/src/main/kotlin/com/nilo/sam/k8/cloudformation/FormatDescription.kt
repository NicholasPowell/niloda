package com.nilo.sam.k8.cloudformation

object FormatDescription {
    operator fun invoke(description: String) =
            """Description: $description"""
}