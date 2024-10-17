package com.app.storitest.core

fun Double.Companion.default() = 0.0

fun Double?.orDefaultDouble() = this ?: 0.0

fun Double.isZero() = this == 0.0

fun Double.isGreaterZero() = this > ZERO
