package com.app.storitest.ui.features.auth.signup

import androidx.compose.runtime.Composable
import com.app.storitest.ui.composables.form.personalData.PersonalDataUi

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    onRegisterClick: (personalDataUi: PersonalDataUi, password: String) -> Unit
) {
    SignUpScaffold(
        onBackClick = onBackClick,
        onRegisterClick = onRegisterClick
    )
}