package com.app.storitest.ui.composables.form.personalData

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.app.storitest.ui.composables.TextFieldState
import com.app.storitest.ui.composables.form.email.EmailFieldState
import com.app.storitest.ui.composables.form.email.rememberEmailFieldState
import com.app.storitest.ui.composables.rememberTextFieldState

@Stable
class PersonalDataFormState(
    val firstName: TextFieldState,
    val lastName: TextFieldState,
    val email: EmailFieldState
) {

    val isValid: Boolean by derivedStateOf { firstName.isValid && lastName.isValid && email.isValid }

    var personalDataUi: PersonalDataUi
        get() = PersonalDataUi(
            firstName = firstName.value,
            lastName = lastName.value,
            email = email.value
        )
        set(value) {
            firstName.value = value.firstName
            lastName.value = value.lastName
            email.onValueChanged(value.email)

        }
}

@Composable
fun rememberPersonalDataFormState(): PersonalDataFormState {
    val firstName = rememberTextFieldState()
    val lastName = rememberTextFieldState()
    val email = rememberEmailFieldState()
    return remember(firstName, lastName, email, ) {
        PersonalDataFormState(firstName, lastName, email)
    }
}