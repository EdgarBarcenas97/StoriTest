package com.app.storitest.ui.features.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.app.storitest.R
import com.app.storitest.ui.composables.form.personalData.PersonalDataUi
import com.app.storitest.ui.composables.form.registration.RegistrationForm
import com.app.storitest.ui.composables.form.registration.rememberRegistrationFormState
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Weight1

@Composable
fun FormContent(
    onRegisterClick: (personalDataUi: PersonalDataUi, password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize()
    ) {
        val formState = rememberRegistrationFormState()
        RegistrationForm(
            state = formState,
            modifier = Modifier.padding(Space16)
        )
        Spacer(modifier = Modifier.weight(Weight1))
        Button(
            onClick = {
                onRegisterClick(
                    formState.personalData.personalDataUi,
                    formState.passwords.firstPassword.value
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Space16),
            enabled = formState.isValid,
        ) {
            Text(text = stringResource(R.string.register))
        }
    }
}
