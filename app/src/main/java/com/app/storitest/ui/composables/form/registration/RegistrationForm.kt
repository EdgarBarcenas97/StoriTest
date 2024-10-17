package com.app.storitest.ui.composables.form.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.storitest.ui.composables.form.password.PasswordsForm
import com.app.storitest.ui.composables.form.personalData.PersonalDataForm
import com.app.storitest.ui.theme.Space16

@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
    state: RegistrationFormState = rememberRegistrationFormState()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Space16)
    ) {
        PersonalDataForm(state = state.personalData)
        PasswordsForm(state = state.passwords)
    }
}
