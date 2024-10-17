package com.app.storitest.ui.composables.form.password

import com.app.storitest.core.extensions.isValidPassword

fun validatePassword(value: String): PasswordValidationResult {
    return when {
        value.isEmpty() -> PasswordValidationResult.EMPTY
        value.isValidPassword() -> PasswordValidationResult.VALID
        else -> PasswordValidationResult.INVALID
    }
}

fun isPasswordValid(value: String): Boolean {
    return validatePassword(value) == PasswordValidationResult.VALID
}

fun arePasswordsValid(first: String, second: String): Boolean {
    return isPasswordValid(first) && isPasswordValid(second) && first == second
}

enum class PasswordValidationResult {
    VALID, EMPTY, INVALID
}
