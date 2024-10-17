package com.app.storitest.core

fun String.Companion.empty() = ""

fun String.Companion.space() = " "

fun String.capitalizeFirstChar() = replaceFirstChar { it.uppercaseChar() }
