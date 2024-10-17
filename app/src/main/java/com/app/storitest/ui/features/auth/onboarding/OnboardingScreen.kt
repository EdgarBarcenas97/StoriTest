package com.app.storitest.ui.features.auth.onboarding

import androidx.compose.runtime.Composable

@Composable
fun OnboardingScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    OnboardingScaffold(
        onSignInClick = onSignInClick,
        onSignUpClick = onSignUpClick
    )
}
