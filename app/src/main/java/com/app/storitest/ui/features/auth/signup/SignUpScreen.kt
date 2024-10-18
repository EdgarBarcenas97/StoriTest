package com.app.storitest.ui.features.auth.signup

import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit
) {
    SignUpScaffold(
        onRegisterClick = { personalData, password -> },
        onBackClick = onBackClick)
}
