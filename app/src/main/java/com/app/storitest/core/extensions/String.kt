package com.app.storitest.core.extensions

import java.util.regex.Pattern

fun String.Companion.empty() = ""

fun String.Companion.space() = " "

fun String.capitalizeFirstChar() = replaceFirstChar { it.uppercaseChar() }

fun String.isValidEmail() = Pattern.compile(EMAIL_PATTERN).matcher(this).matches()

fun String.isValidPassword() = Pattern.compile(PASSWORD_PATTERN).matcher(this).matches()

const val EMAIL_PATTERN = "[a-zA-Z0-9+._%\\-]{1,256}" +
    "@" +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
    "(" +
    "\\." +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
    ")+"

const val PASSWORD_PATTERN = "^" +
    "(?=.*[0-9])" +
    "(?=.*[a-z])" +
    "(?=.*[A-Z])" +
    "(?=.*[a-zA-Z])" +
    "(?=.*[-_@#$%^&+=])" +
    "(?=\\S+$)" +
    ".{8,}" +
    "$"

fun List<String>.addBullets() = joinToString(separator = "\n\u2022 ", prefix = "\u2022 ") { it }
