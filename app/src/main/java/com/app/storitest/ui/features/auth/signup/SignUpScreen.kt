package com.app.storitest.ui.features.auth.signup

import androidx.compose.runtime.Composable
import com.app.storitest.ui.composables.form.personalData.PersonalData

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    onRegisterClick: (personalData: PersonalData, password: String) -> Unit
) {
    SignUpScaffold(
        onBackClick = onBackClick,
        onRegisterClick = onRegisterClick
    )
}