package com.app.storitest.ui.features.auth.signin

import androidx.compose.runtime.Composable

@Composable
fun SignInScreen(
    onBackClick: () -> Unit
) {
    SignInScaffold(onBackClick = onBackClick)
}
