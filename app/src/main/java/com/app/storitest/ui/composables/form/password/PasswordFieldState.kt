package com.app.storitest.ui.composables.form.password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.app.storitest.core.extensions.default
import com.app.storitest.core.extensions.empty

@Stable
class PasswordFieldState(initialValue: String) {

    var value: String by mutableStateOf(initialValue)
        private set

    var error: String? by mutableStateOf(null)
        private set

    val isValid: Boolean by derivedStateOf {
        isPasswordValid(value)
    }

    fun onValueChanged(newValue: String) {
        value = newValue
        error = when (validatePassword(newValue)) {
            PasswordValidationResult.VALID -> null
            PasswordValidationResult.INVALID -> "Password is invalid"
            PasswordValidationResult.EMPTY -> "Password cannot be empty"
        }
    }
}

private val passwordFieldSaver = listSaver(
    save = { listOf(it.value) },
    restore = { PasswordFieldState(it[Int.default()]) }
)

@Composable
fun rememberPasswordFieldState(): PasswordFieldState {
    return rememberSaveable(saver = passwordFieldSaver) {
        PasswordFieldState(initialValue = String.empty())
    }
}