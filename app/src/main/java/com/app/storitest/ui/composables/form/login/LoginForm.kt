package com.app.storitest.ui.composables.form.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.storitest.ui.composables.form.password.PasswordField
import com.app.storitest.ui.composables.form.email.EmailField
import com.app.storitest.ui.theme.Space24

@Composable
fun LoginForm(
    state: LoginFormState = rememberLoginFormState(),
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Space24),
        modifier = Modifier.fillMaxWidth().then(modifier)
    ) {
        EmailField(state = state.email)
        PasswordField(state = state.password)
    }
}
