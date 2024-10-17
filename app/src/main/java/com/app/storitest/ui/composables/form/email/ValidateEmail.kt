package com.app.storitest.ui.composables.form.email

import com.app.storitest.core.extensions.isValidEmail

fun validateEmail(email: String): EmailValidationResult {
    return when {
        email.isEmpty() -> EmailValidationResult.EMPTY
        email.isValidEmail() -> EmailValidationResult.VALID
        else -> EmailValidationResult.INVALID_FORMAT
    }
}

fun isEmailValid(email: String): Boolean {
    return validateEmail(email) == EmailValidationResult.VALID
}

enum class EmailValidationResult {
    VALID, EMPTY, INVALID_FORMAT
}
