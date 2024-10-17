package com.app.storitest.core

fun Boolean.Companion.default() = false

fun Boolean?.orDefault(default: Boolean = false) = this ?: default

fun Boolean?.orTrue(default: Boolean = true) = this ?: default
