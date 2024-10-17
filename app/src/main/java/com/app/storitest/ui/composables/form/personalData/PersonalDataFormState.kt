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
    val email: EmailFieldState,
    val phoneNumber: TextFieldState
) {

    val isValid: Boolean by derivedStateOf { firstName.isValid && lastName.isValid && email.isValid && phoneNumber.isValid }

    var personalData: PersonalData
        get() = PersonalData(
            firstName = firstName.value,
            lastName = lastName.value,
            email = email.value,
            phoneNumber = phoneNumber.value,
        )
        set(value) {
            firstName.value = value.firstName
            lastName.value = value.lastName
            email.onValueChanged(value.email)
            phoneNumber.value = value.phoneNumber
        }
}

@Composable
fun rememberPersonalDataFormState(): PersonalDataFormState {
    val firstName = rememberTextFieldState()
    val lastName = rememberTextFieldState()
    val email = rememberEmailFieldState()
    val phoneNumber = rememberTextFieldState()
    return remember(firstName, lastName, email, phoneNumber) {
        PersonalDataFormState(firstName, lastName, email, phoneNumber)
    }
}