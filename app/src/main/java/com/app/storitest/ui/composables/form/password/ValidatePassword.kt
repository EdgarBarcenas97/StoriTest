package com.app.storitest.ui.composables.form.password

fun validatePassword(value: String): PasswordValidationResult {
    return when {
        value.isEmpty() -> PasswordValidationResult.EMPTY
        else -> PasswordValidationResult.VALID
    }
}

fun isPasswordValid(value: String): Boolean {
    return validatePassword(value) == PasswordValidationResult.VALID
}

fun arePasswordsValid(first: String, second: String): Boolean {
    return isPasswordValid(first) && isPasswordValid(second) && first == second
}

enum class PasswordValidationResult {
    VALID, EMPTY
}
