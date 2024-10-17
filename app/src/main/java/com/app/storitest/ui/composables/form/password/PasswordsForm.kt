package com.app.storitest.ui.composables.form.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.app.storitest.R
import com.app.storitest.ui.theme.Space24

@Composable
fun PasswordsForm(
    modifier: Modifier = Modifier,
    state: PasswordsFormState = rememberNewPasswordFormState(),
    firstLabel: String = stringResource(R.string.password),
    secondLabel: String = stringResource(R.string.repeat_password)
) {
    Column(modifier = modifier) {
        PasswordField(state = state.firstPassword, label = firstLabel)
        Spacer(modifier = Modifier.height(Space24))
        PasswordField(state = state.secondPassword, label = secondLabel)
    }
}