package com.app.storitest.ui.features.auth.onboarding

import androidx.compose.runtime.Composable

@Composable
fun OnboardingScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    OnboardingContent(
        onSignInClick = onSignInClick,
        onSignUpClick = onSignUpClick
    )
}
