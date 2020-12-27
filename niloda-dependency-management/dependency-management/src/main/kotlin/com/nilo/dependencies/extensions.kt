package com.nilo.dependencies

fun String.stripVersion() = this.subSequence(0, this.lastIndexOf(':'))

